package com.restaurant.Restaurant.Model;

public class Restaurant {
    private final int id;
    private final int brandId;
    private final String adress;
    private final int seatCount;
    private final int menuId;

    public Restaurant(int id, int brandId, String adress, int seatCount, int menuId) {
        this.id = id;
        this.brandId = brandId;
        this.adress = adress;
        this.seatCount = seatCount;
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getAdress() {
        return adress;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public int getMenuId() {
        return menuId;
    }
}
