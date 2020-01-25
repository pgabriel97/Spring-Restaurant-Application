package com.restaurant.Restaurant.Model;

public class Rating {
    private int restaurantID;
    private String userID;
    private int grade;

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Rating() {
        this.restaurantID = 0;
        this.userID = "";
        this.grade = 0;
    }

    public Rating(int restaurantID, String userID) {
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.grade = 0;
    }

    public Rating(int restaurantID, String userID, int grade) {
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.grade = grade;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public String getUserID() {
        return userID;
    }

    public int getGrade() {
        return grade;
    }
}
