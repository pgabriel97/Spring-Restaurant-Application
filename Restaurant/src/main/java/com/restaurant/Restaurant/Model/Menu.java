package com.restaurant.Restaurant.Model;

public class Menu {

    private final int id;
    private final int orderNo;
    private final String foodName;
    private final int price;

    public Menu(int id, int orderNo, String foodName, int price) {
        this.id = id;
        this.orderNo = orderNo;
        this.foodName = foodName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getPrice() {
        return price;
    }
}
