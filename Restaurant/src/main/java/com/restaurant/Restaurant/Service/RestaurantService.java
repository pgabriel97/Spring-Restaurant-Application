package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Comment;
import com.restaurant.Restaurant.Model.Franchise;
import com.restaurant.Restaurant.Model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    public Restaurant getRestaurantById(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from restaurant where id = " + id);
        ResultSet rs = pst.executeQuery();

        rs.next();

        Restaurant restaurant = new Restaurant(rs.getInt(1),
                    rs.getInt(2), rs.getString(3), rs.getString(4),
                    rs.getInt(5), rs.getInt(6), rs.getString(8));

        return restaurant;
    }

    public float getRestaurantGrade(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        // Getting all ratings for this restaurant from table Ratings
        PreparedStatement pst = c.prepareStatement("SELECT AVG(grade) as gr from rating where id_restaurant = " + id);
        ResultSet rs = pst.executeQuery();

        rs.next();

        // Returning the average rating to be displayed on browser
        return rs.getFloat("gr");
    }

    public List<Comment> getRestaurantComments(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from comment where id_restaurant = " + id);
        ResultSet rs = pst.executeQuery();

        List<Comment> commentList = new ArrayList<>();

        while (rs.next()) {
            Comment comment = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3));
            commentList.add(comment);
        }

        return commentList;
    }


    public Franchise getFranchiseByRestaurantId(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT brand_id FROM restaurant WHERE id = " + id);
        ResultSet rs = pst.executeQuery();

        rs.next();
        int brandId = rs.getInt(1);
        pst = c.prepareStatement("SELECT * FROM franchise WHERE id = " + brandId);
        rs = pst.executeQuery();

        rs.next();
        Franchise franchise = new Franchise(rs.getInt(1), rs.getString(2),
                rs.getString(3));

        return franchise;
    }
}
