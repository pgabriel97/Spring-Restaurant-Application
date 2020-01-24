package com.restaurant.Restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RestaurantApplication {

	static final public String[] sqlCommands = new String[]
	{
			"DROP TABLE IF EXISTS franchise",
			"DROP TABLE IF EXISTS restaurant",
			"DROP TABLE IF EXISTS menu",
			"DROP TABLE IF EXISTS rating",
			"DROP TABLE IF EXISTS user_roles",
			"DROP TABLE IF EXISTS users",


			/*"CREATE TABLE IF NOT EXISTS FRANCHISE (ID INT PRIMARY KEY NOT NULL, " +
				"NAME TEXT NOT NULL, TYPE TEXT NOT NULL, GRADE FLOAT(2) NOT NULL);",
			"CREATE TABLE IF NOT EXISTS RESTAURANT (ID INT PRIMARY KEY NOT NULL, " +
					"BRAND_ID INT NOT NULL, NAME TEXT NOT NULL, ADDRESS TEXT NOT NULL," +
					"SEAT_COUNT INT NOT NULL, MENU_ID INT NOT NULL, GRADE FLOAT(2) NOT NULL);",
			"CREATE TABLE IF NOT EXISTS MENU (ID INT NOT NULL, ORDER_NO INT NOT NULL, " +
					"FOOD_NAME TEXT NOT NULL, PRICE INT NOT NULL, PRIMARY KEY (ID, ORDER_NO));",
			"CREATE TABLE IF NOT EXISTS RATING (ID_RESTAURANT INT NOT NULL, ID_USER INT NOT NULL, " +
					"GRADE INT NOT NULL, PRIMARY KEY (ID_RESTAURANT, ID_USER));",
			"CREATE TABLE IF NOT EXISTS USERS(username varchar(20) NOT NULL," +
					"password varchar(20) NOT NULL,enabled boolean NOT NULL DEFAULT FALSE," +
					"primary key(username));",
			"create table IF NOT EXISTS USER_ROLES (user_role_id SERIAL PRIMARY KEY, username varchar(20) NOT NULL, " +
					"role varchar(20) NOT NULL,UNIQUE (username,role), " +
					"FOREIGN KEY (username) REFERENCES users (username));",

			"INSERT INTO franchise (id, name, type, grade) VALUES (1, 'Mc', 'Fast food', 8.5), " +
					"(2, 'KFC', 'Fast food', 0), " +
					"(3, 'Pizza Hut', 'Pizza', 0);",
			"INSERT INTO restaurant (id, brand_id, name, address, seat_count, menu_id, grade)" +
					" VALUES (1, 1, 'McDonalds Unirii', 'Unirii', 30, 1, 8.5), (2, 1, 'KFC Pacii', 'Pacii', 20, 1, 0)," +
					"(3, 2, 'Pizza Hut ParkLake', 'Dristor', 10, 2, 0);",
			"INSERT INTO menu (id, order_no, food_name, price)" +
					" VALUES (1, 1, 'Sandwich', 15), (1, 2, 'Salad', 10), (2, 1, 'Shawarma', 17), (3, 1, 'Pizza', 23), (2, 2, 'Cake', 5);",
			"INSERT INTO rating (id_restaurant, id_user, grade)" +
					" VALUES (1, 1, 9), (1, 2, 8)",

            /*"INSERT INTO users(username,password,enabled)" +
					" VALUES ('ana','{noop}ana', true);",
			"INSERT INTO users(username,password,enabled) " +
					"VALUES ('gabi','{noop}gabi', true);",
			"INSERT INTO users(username,password,enabled) " +
					"VALUES ('enduser','{noop}enduser', true);",
			"INSERT INTO user_roles (username, role) " +
					"VALUES ('ana', 'ROLE_ADMIN');",
			"INSERT INTO user_roles (username, role) " +
					"VALUES ('gabi', 'ROLE_ADMIN');",
			"INSERT INTO user_roles (username, role)" +
					" VALUES ('enduser', 'ROLE_USER');"
					*/
	};

	public static void main(String[] args) throws SQLException {

		System.out.println(new BCryptPasswordEncoder().encode("123"));
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