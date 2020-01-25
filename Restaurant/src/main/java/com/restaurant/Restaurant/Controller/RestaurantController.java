package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.*;
import com.restaurant.Restaurant.Service.MenuService;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        model.addAttribute("reservation", "How many seats?");
        model.addAttribute("rating", "How many seats?");
        model.addAttribute("averageRating", restaurantService.getRestaurantGrade(id));
        model.addAttribute("srcImageURL", "../static/images/" + restaurantData.getImageURL());
        model.addAttribute("thSrcImageURL", "/images/" +restaurantData.getImageURL());

        model.addAttribute("userID", "1");

        // SENDING RATING OBJECT
        Rating rating = new Rating(id, 1);
        model.addAttribute("rating", rating);

        // SENDING COMMENT LIST FOR DISPLAY
        List<Comment> commentList = restaurantService.getRestaurantComments(restaurantData.getId());
        model.addAttribute("commentList", commentList);

        // CREATING EMPTY COMMENT TO BE PROCESSED AND SENT
        model.addAttribute("emptyComment", new Comment(id, 1, ""));

        // SENDING MENU LIST FOR DISPLAY
        List<Menu> menuList = menuService.getMenuById(restaurantData.getMenuId());
        model.addAttribute("menuList", menuList);

        return "restaurant";
    }

    @PostMapping("/restaurant/submitRank")
    public String submitRank(@ModelAttribute Rating rating) throws SQLException {

        System.out.println("RATING TRIMIS: " + rating.getRestaurantID() + " " + rating.getUserID());
        System.out.println(rating.getGrade());

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        Statement st = c.createStatement();
        st.executeUpdate("INSERT INTO rating (id_restaurant, id_user, grade) values (" + rating.getRestaurantID() + ", " +
                rating.getUserID() + ", " + rating.getGrade() + ")");
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
}
