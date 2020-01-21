package com.restaurant.Restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.sql.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RestaurantApplication {

	static final public String[] sqlCommands = new String[]
	{
			"DROP TABLE IF EXISTS restaurant",
		"CREATE TABLE IF NOT EXISTS RESTAURANT (ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL," +
				" ADDRESS TEXT NOT NULL, TYPE TEXT NOT NULL);",
			"INSERT INTO restaurant (id, name, address, type) VALUES (1, 'Mc', 'Unirii', 'Fast food'), " +
					"(2, 'KFC', 'Lujerului', 'Fast food'), " +
					"(3, 'Pizza Hut', 'Berceni', 'Pizza');"
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
