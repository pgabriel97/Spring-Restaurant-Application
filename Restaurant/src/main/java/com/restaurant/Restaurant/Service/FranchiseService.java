package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Franchise;
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
}
