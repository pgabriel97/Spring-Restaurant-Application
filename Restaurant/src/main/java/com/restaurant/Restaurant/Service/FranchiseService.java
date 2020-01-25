package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Franchise;
import com.restaurant.Restaurant.Model.Restaurant;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FranchiseService {

    public Franchise getFranchiseById(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from franchise where id = " + id);
        ResultSet rs = pst.executeQuery();

        rs.next();
        Franchise franchise = new Franchise(rs.getInt(1), rs.getString(2),
                rs.getString(3));

        return franchise;
    }

    public List<Franchise> getAllFranchises() throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from franchise");
        ResultSet rs = pst.executeQuery();

        List<Franchise> franchiseList = new ArrayList<>();

        while (rs.next()) {
            Franchise franchise = new Franchise(rs.getInt(1), rs.getString(2),
                    rs.getString(3));
            franchiseList.add(franchise);
        }

        return franchiseList;
    }

    public float getFranchiseGrade(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT AVG(grade) as gr from RESTAURANT where brand_id = " + id +
                "AND grade <> 0");
        ResultSet rs = pst.executeQuery();

        rs.next();
        System.out.println(rs.getFloat("gr"));

        return rs.getFloat("gr");
    }

    public List<Restaurant> getRestaurantsByFranchiseId(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from restaurant where brand_id = " + id + " ORDER BY grade");
        ResultSet rs = pst.executeQuery();

        List<Restaurant> restaurantList = new ArrayList<>();

        while (rs.next()) {
            Restaurant restaurant = new Restaurant(rs.getInt(1),
                    rs.getInt(2), rs.getString(3), rs.getString(4),
                    rs.getInt(5), rs.getInt(6), rs.getString(8));
            restaurantList.add(restaurant);
        }

        return restaurantList;
    }
}
