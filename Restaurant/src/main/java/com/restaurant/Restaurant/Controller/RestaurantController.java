package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.*;
import com.restaurant.Restaurant.Service.MenuService;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/restaurant/{id}")
    public String loadRestaurantPage(Model model, @PathVariable int id) throws SQLException {

        Restaurant restaurantData = restaurantService.getRestaurantById(id);
        Franchise franchiseData = restaurantService.getFranchiseByRestaurantId(id);

        model.addAttribute("restaurantId", restaurantData.getId());
        model.addAttribute("restaurantName", restaurantData.getName());
        model.addAttribute("restaurantAddress", restaurantData.getAddress());
        model.addAttribute("restaurantSeats", restaurantData.getSeatCount());
        model.addAttribute("franchiseName", franchiseData.getName());
        model.addAttribute("franchiseType", franchiseData.getType());
        model.addAttribute("franchiseID", franchiseData.getId());
        model.addAttribute("reservation", "How many seats?");
        model.addAttribute("rating", "How many seats?");
        model.addAttribute("averageRating", restaurantService.getRestaurantGrade(id));
        model.addAttribute("srcImageURL", "../static/images/" + restaurantData.getImageURL());
        model.addAttribute("thSrcImageURL", "/images/" +restaurantData.getImageURL());

        model.addAttribute("userID", "1");

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // SENDING RATING OBJECT
        Rating rating = new Rating(id, userDetails.getUsername());
        model.addAttribute("rating", rating);

        // SENDING COMMENT LIST FOR DISPLAY
        List<Comment> commentList = restaurantService.getRestaurantComments(restaurantData.getId());
        model.addAttribute("commentList", commentList);

        // CREATING EMPTY COMMENT TO BE PROCESSED AND SENT
        model.addAttribute("emptyComment", new Comment(id, userDetails.getUsername(), ""));

        // SENDING MENU LIST FOR DISPLAY
        List<Menu> menuList = menuService.getMenuById(restaurantData.getMenuId());
        model.addAttribute("menuList", menuList);

        model.addAttribute("currentUser", userDetails.getUsername());

        Rating currentRating = restaurantService.getRatingOfUserForRestaurant(id, userDetails.getUsername());

        if (currentRating != null) {
            model.addAttribute("currentRating", currentRating);
            System.out.println("NOTA DATA AICI ESTE: " + restaurantService.getRatingOfUserForRestaurant(id, userDetails.getUsername()).getGrade());
        }
        else {
            model.addAttribute("currentRating", null);
            System.out.println("NOTA DATA AICI ESTE: null");
        }

        return "restaurant";
    }

    
    @GetMapping("/restaurant")
    public String loadRestaurantListPage(Model model) throws SQLException {

        List<Restaurant> restaurantList = restaurantService.getRestaurantsByRating();

        model.addAttribute("restaurantList", restaurantList);

        return "main_restaurant";
    }



    @PostMapping("/restaurant/submitRank")
    public String submitRank(@ModelAttribute Rating rating) throws SQLException {

        System.out.println("RATING TRIMIS: " + rating.getRestaurantID() + " " + rating.getUserID());
        System.out.println(rating.getGrade());

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        Statement st = c.createStatement();
        st.executeUpdate("INSERT INTO rating (id_restaurant, username, grade) values (" + rating.getRestaurantID() + ", '" +
                rating.getUserID() + "', " + rating.getGrade() + ")");
        c.close();

        return "redirect:/restaurant/" + rating.getRestaurantID();
    }


    @PostMapping("/restaurant/updateRank")
    public String updateRank(@ModelAttribute Rating rating) throws SQLException {

        System.out.println("RATING DE UPDATAT: " + rating.getRestaurantID() + " " + rating.getUserID());
        System.out.println(rating.getGrade());

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        Statement st = c.createStatement();
        st.executeUpdate("UPDATE rating SET grade = " + rating.getGrade() + " where username = '"
        + rating.getUserID() + "' and id_restaurant = " + rating.getRestaurantID());
        c.close();

        return "redirect:/restaurant/" + rating.getRestaurantID();
    }


    @PostMapping("/restaurant/submitComment")
    public String submitComment(@ModelAttribute Comment comment, @RequestParam(value = "restaurantId",  required = false)
            Integer restaurantId, @RequestParam(value = "userID", required = false) Integer userID) throws SQLException {

        System.out.println("COMENTARIU TRIMIS: " + comment.getRestaurantID() + " " + comment.getUserID());
        System.out.println(comment.getComment());

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        Statement st = c.createStatement();
        st.executeUpdate("INSERT INTO comment (id_comment, id_restaurant, id_user, comment_text)" +
                " values ('" + UUID.randomUUID() + "', " + comment.getRestaurantID() + ", " + comment.getUserID() + ", '" + comment.getComment() + "')");
        c.close();

        return "redirect:/restaurant/" + comment.getRestaurantID();
    }

    @RequestMapping(value = "/restaurant/deleteComment/{restaurantID}/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable int restaurantID, @PathVariable String id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        System.out.println(id);
        Statement stmt = c.createStatement();
        stmt.execute("DELETE FROM COMMENT WHERE id_comment = '" + id + "'");
        c.close();
        return "redirect:/restaurant/" + restaurantID;
    }


    @GetMapping("/restaurant/addRestaurant")
    public String addRestaurant(Model model) throws SQLException {

        Restaurant restaurant = new Restaurant();
        model.addAttribute("emptyRestaurant", restaurant);

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * FROM franchise");
        ResultSet rs = pst.executeQuery();

        List<Franchise> franchiseList = new ArrayList<>();

        while (rs.next()) {
            Franchise franchise = new Franchise(rs.getInt(1), rs.getString(2), rs.getString(3));
            franchiseList.add(franchise);
        }

        c.close();

        model.addAttribute("franchiseList", franchiseList);

        return "add_restaurant";
    }


    @PostMapping("/restaurant/addRestaurant")
    public String addRestaurant(@ModelAttribute("emptyRestaurant") Restaurant restaurant) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT MAX(id) FROM restaurant");
        ResultSet rs = pst.executeQuery();

        rs.next();
        int newID = rs.getInt(1) + 1;

        System.out.println("APARTINE DE FRANCIZA: " + restaurant.getBrandId());

        Statement st = c.createStatement();
        st.executeUpdate("INSERT INTO restaurant (id, brand_id, name, address, seat_count, menu_id, grade, img)" +
                " values (" + newID + ", " + restaurant.getBrandId() + ", '" + restaurant.getName() + "', '" + restaurant.getAddress() + "', " +
                restaurant.getSeatCount() + ", 1, 0, 'default.png')");
        c.close();

        return "redirect:/restaurant/" + newID;
    }



    @GetMapping("/restaurant/editRestaurant/{id}")
    public String editRestaurant(Model model, @PathVariable int id) throws SQLException {

        RestaurantService restaurantService = new RestaurantService();

        Restaurant restaurant = restaurantService.getRestaurantById(id);

        model.addAttribute("id", id);
        model.addAttribute("myRestaurant", restaurant);

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * FROM franchise");
        ResultSet rs = pst.executeQuery();

        List<Franchise> franchiseList = new ArrayList<>();

        while (rs.next()) {
            Franchise franchise = new Franchise(rs.getInt(1), rs.getString(2), rs.getString(3));
            franchiseList.add(franchise);
        }

        c.close();

        model.addAttribute("franchiseList", franchiseList);

        return "edit_restaurant";
    }

    @RequestMapping(value = "/restaurant/editRestaurant/{idd}", method=RequestMethod.POST)
    public String editRestaurant(@ModelAttribute("myRestaurant")Restaurant restaurant, @PathVariable String idd, BindingResult bindingResult) throws SQLException, InterruptedException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        System.out.println("ID-UL ESTE   "+ idd);
        Statement st = c.createStatement();

        System.out.println("Eeeeee" + restaurant.getName());

        String sql = "UPDATE restaurant set name = '" + restaurant.getName() + "', address = '" + restaurant.getAddress() + "', brand_id = " + restaurant.getBrandId() + ", seat_count = " + restaurant.getSeatCount() + " where id = " + restaurant.getId();
        System.out.println("SQL   " + sql);
        st.executeUpdate(sql);
        //st.executeUpdate("UPDATE restaurant SET name = 'xxxxx' where id = 1");

        c.close();

        return "redirect:/restaurant/" + restaurant.getId();
    }
}
