package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Restaurant;
import org.springframework.stereotype.Service;

import java.sql.*;
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

    public Restaurant getRestaurantById(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from restaurant where id = " + id);
        ResultSet rs = pst.executeQuery();

        rs.next();
        Restaurant restaurant = new Restaurant(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getString(4));

        return restaurant;
    }
}
