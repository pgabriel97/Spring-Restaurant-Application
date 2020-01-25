package com.restaurant.Restaurant.Model;


import javax.persistence.*;

public class Comment {

    private String commentID;

    private int restaurantID;

    private int userID;

    private String comment;

    public Comment() {
        this.restaurantID = 0;
        this.userID = 0;
        this.comment = "";
    }

    public Comment(int restaurantID, int userID, String comment) {
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.comment = comment;
    }

    public Comment(String commentID, int restaurantID, int userID, String comment) {
        this.commentID = commentID;
        this.restaurantID = restaurantID;
        this.userID = userID;
        this.comment = comment;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
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
