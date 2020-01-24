package com.restaurant.Restaurant.Model;

public class Comment {
    private int restaurantID;
    private int userID;
    private String comment;

    public Comment(int restaurantID, int userID, String comment) {
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.comment = comment;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
