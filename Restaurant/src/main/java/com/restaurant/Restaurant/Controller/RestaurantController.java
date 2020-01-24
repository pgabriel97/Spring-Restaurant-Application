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

        model.addAttribute("restaurantId", id);
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

        Rating rating = new Rating(id, 1);
        model.addAttribute("rating", rating);

        Calendar now = Calendar.getInstance();

        List<Menu> menuList = menuService.getMenuById(restaurantData.getMenuId());
        model.addAttribute("menuList", menuList);

        List<Comment> commentList = restaurantService.getRestaurantComments(restaurantData.getMenuId());
        model.addAttribute("commentList", commentList);

        return "restaurant";
    }

    @PostMapping("/restaurant/submitRank")
    public String greetingSubmit(@ModelAttribute Rating rating) throws SQLException {
//        rating.setRestaurantID(restaurantID);
//        rating.setUserID(userID);
        System.out.println(rating.getGrade() + " " + rating.getRestaurantID() + " " + rating.getUserID());

        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        Statement st = c.createStatement();
        st.executeUpdate("INSERT INTO rating (id_restaurant, id_user, grade) values (1, 1, 1)");
        c.close();

        return "redirect:/restaurant/" + 1;
    }
}
