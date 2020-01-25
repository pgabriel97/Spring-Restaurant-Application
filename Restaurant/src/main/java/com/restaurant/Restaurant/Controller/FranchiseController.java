package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Comment;
import com.restaurant.Restaurant.Model.Franchise;
import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Model.User;
import com.restaurant.Restaurant.Service.FranchiseService;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.*;
import java.util.List;

@Controller
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;
    private RestaurantService restaurantService;


    @GetMapping("/franchise/{id}")
    public String loadFranchisePage(Model model, @PathVariable int id) throws SQLException {

        Franchise franchiseData = franchiseService.getFranchiseById(id);
        model.addAttribute("franchiseName", franchiseData.getName());
        model.addAttribute("franchiseType", franchiseData.getType());
        model.addAttribute("franchiseGrade", franchiseService.getFranchiseGrade(id));

        List<Restaurant> restaurantList = franchiseService.getRestaurantsByFranchiseId(franchiseData.getId());
        model.addAttribute("restaurantList", restaurantList);

        return "franchise";
    }

    @GetMapping("/franchise")
    public String loadMainFranchisePage(Model model) throws SQLException {

        List<Franchise> franchiseList = franchiseService.getAllFranchises();

        model.addAttribute("franchiseList", franchiseList);

        return "main_franchise";
    }
}