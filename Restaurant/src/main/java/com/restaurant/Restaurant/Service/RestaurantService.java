package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {

    private List<Restaurant> restaurants = Arrays.asList(
            new Restaurant(1, "McDonalds", "Unirii", "Fast Food"),
            new Restaurant(2, "KFC", "Universitate", "Fast Food"),
            new Restaurant(3, "La Placinte", "Romana", "General"));

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public Restaurant getRestaurantById(int id) {
        return restaurants.stream().filter(r -> r.getId() == id).findFirst().get();
    }
}
