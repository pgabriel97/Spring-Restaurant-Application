package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> allRestaurantsPage() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant restaurantPage(@PathVariable int id) {
        return restaurantService.getRestaurantById(id);
    }
}