package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String homePage()
    {
        return "home";
    }

    @GetMapping("/restaurants")
    public List<Restaurant> allRestaurantsPage() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public String loadRestaurantPage(Model model, @PathVariable int id) throws SQLException {

        Restaurant restaurantData = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurantName", restaurantData.getName());
        model.addAttribute("restaurantAddress", restaurantData.getAddress());
        model.addAttribute("restaurantType", restaurantData.getType());
        return "restaurant";
    }
}