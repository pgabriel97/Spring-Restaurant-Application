package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Franchise;
import com.restaurant.Restaurant.Model.Menu;
import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Model.User;
import com.restaurant.Restaurant.Service.MenuService;
import com.restaurant.Restaurant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
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

        Calendar now = Calendar.getInstance();

        List<Menu> menuList = menuService.getMenuById(restaurantData.getMenuId());

        model.addAttribute("menuList", menuList);

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//
//        System.out.println(name);

        return "restaurant";
    }

    @PostMapping("/addRating")
    public String rateRestaurant(Integer ratingGrade) throws SQLException {

        System.out.println("Sending new restaurant grade...");

//        Restaurant restaurantData = restaurantService.getRestaurantById(id);
//        Franchise franchiseData = restaurantService.getFranchiseByRestaurantId(id);
//
//        model.addAttribute("restaurantId", id);
//        model.addAttribute("restaurantAddress", restaurantData.getAdress());
//        model.addAttribute("restaurantSeats", restaurantData.getSeatCount());
//        model.addAttribute("franchiseName", franchiseData.getName());
//        model.addAttribute("franchiseType", franchiseData.getType());
//        model.addAttribute("reservation", "How many seats?");
//        model.addAttribute("rating", "How many seats?");
//
//        Calendar now = Calendar.getInstance();
//
//        List<Menu> menuList = menuService.getMenuById(restaurantData.getMenuId());
//
//        model.addAttribute("menuList", menuList);

        return "restaurant";
    }
}
