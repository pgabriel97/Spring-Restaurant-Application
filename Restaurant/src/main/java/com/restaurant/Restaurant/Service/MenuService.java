package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Menu;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    public List<Menu> getMenuById(int id) throws SQLException {
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/restaurant",
                        "postgres", "12345");

        PreparedStatement pst = c.prepareStatement("SELECT * from menu where id = " + id + " ORDER BY order_no");
        ResultSet rs = pst.executeQuery();

        rs.next();
        List<Menu> menuList = new ArrayList<>();

        while (rs.next()) {
            Menu menu = new Menu(rs.getInt(1), rs.getInt(2),
                    rs.getString(3), rs.getInt(4));
            menuList.add(menu);
        }

        return menuList;
    }
}
