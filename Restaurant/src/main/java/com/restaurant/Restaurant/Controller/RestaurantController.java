package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Franchise;
import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant/{id}")
    public String loadRestaurantPage(Model model, @PathVariable int id) throws SQLException {

        Restaurant restaurantData = restaurantService.getRestaurantById(id);
        Franchise franchiseData = restaurantService.getFranchiseByRestaurantId(id);

        model.addAttribute("restaurantAddress", restaurantData.getAdress());
        model.addAttribute("restaurantSeats", restaurantData.getSeatCount());
        model.addAttribute("franchiseName", franchiseData.getName());
        model.addAttribute("franchiseType", franchiseData.getType());

        return "restaurant";
    }
}
