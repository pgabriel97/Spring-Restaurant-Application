package com.restaurant.Restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.sql.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RestaurantApplication {

	static final public String[] sqlCommands = new String[]
	{
			"DROP TABLE IF EXISTS franchise",
			"DROP TABLE IF EXISTS restaurant",
			"DROP TABLE IF EXISTS menu",
			"DROP TABLE IF EXISTS food",

		"CREATE TABLE IF NOT EXISTS FRANCHISE (ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL, TYPE TEXT NOT NULL);",
			"CREATE TABLE IF NOT EXISTS RESTAURANT (ID INT PRIMARY KEY NOT NULL, " +
					"BRAND_ID INT NOT NULL, ADDRESS TEXT NOT NULL, SEAT_COUNT INT NOT NULL, MENU_ID INT NOT NULL);",
			"CREATE TABLE IF NOT EXISTS MENU (ID INT NOT NULL, ID_FOOD INT NOT NULL, PRICE INT NOT NULL, PRIMARY KEY (ID, ID_FOOD));",
			"CREATE TABLE IF NOT EXISTS FOOD (ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL);",

			"INSERT INTO franchise (id, name, type) VALUES (1, 'Mc', 'Fast food'), " +
					"(2, 'KFC', 'Fast food'), " +
					"(3, 'Pizza Hut', 'Pizza');",
			"INSERT INTO restaurant (id, brand_id, address, seat_count, menu_id)" +
					" VALUES (1, 1, 'Unirii', 30, 1);",
			"INSERT INTO menu (id, id_food, price)" +
					" VALUES (1, 1, 15);",
			"INSERT INTO food (id, name) VALUES (1, 'Sandwich'), (2, 'Salad'), (3, 'Shawarma'), (4, 'Pizza'), (5, 'Cake');"
	};

	public static void main(String[] args) throws SQLException {

		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/restaurant",
							"postgres", "12345");

			Statement stmt;

			// Create and populate database if it isn't already
			for (String sqlCommand : sqlCommands) {
				stmt = c.createStatement();
				stmt.executeUpdate(sqlCommand);
				stmt.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		SpringApplication.run(RestaurantApplication.class, args);
	}
}