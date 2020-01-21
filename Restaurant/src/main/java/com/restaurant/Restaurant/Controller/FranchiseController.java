package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Franchise;
import com.restaurant.Restaurant.Service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@Controller
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping("/")
    public String homePage()
    {
        return "home";
    }

//    @GetMapping("/restaurants")
//    public List<Restaurant> allRestaurantsPage() {
//        return restaurantService.getAllRestaurants();
//    }

    @GetMapping("/franchise/{id}")
    public String loadFranchisePage(Model model, @PathVariable int id) throws SQLException {

        Franchise franchiseData = franchiseService.getFranchiseById(id);
        model.addAttribute("franchiseName", franchiseData.getName());
        model.addAttribute("franchiseType", franchiseData.getType());
        return "franchise";
    }

    @GetMapping("/franchise")
    public String loadMainFranchisePage(Model model) throws SQLException {

        List<Franchise> franchiseList = franchiseService.getAllFranchises();

        model.addAttribute("franchiseList", franchiseList);

        return "main_franchise";
    }
}