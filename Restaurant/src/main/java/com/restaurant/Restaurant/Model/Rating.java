package com.restaurant.Restaurant.Model;

public class Rating {
    private int restaurantID;
    private int userID;
    private int grade;

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Rating() {
        this.restaurantID = 0;
        this.userID = 0;
        this.grade = 0;
    }

    public Rating(int restaurantID, int userID) {
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.grade = 0;
    }

    public Rating(int restaurantID, int userID, int grade) {
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.grade = grade;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public int getUserID() {
        return userID;
    }

    public int getGrade() {
        return grade;
    }
}
