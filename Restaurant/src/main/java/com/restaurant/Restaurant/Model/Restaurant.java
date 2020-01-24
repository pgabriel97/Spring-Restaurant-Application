package com.restaurant.Restaurant.Model;

public class Restaurant {
    private final int id;
    private final int brandId;
    private final String name;
    private final String address;
    private final int seatCount;
    private final int menuId;
    private final String imageURL;

    public Restaurant(int id, int brandId, String name, String address, int seatCount, int menuId, String imageURL) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.address = address;
        this.seatCount = seatCount;
        this.menuId = menuId;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getImageURL() {
        return imageURL;
    }
}
