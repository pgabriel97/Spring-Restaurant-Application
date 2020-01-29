package com.restaurant.Restaurant.Model;

public class Restaurant {
    private  int id;
    private  int brandId;
    private  String name;
    private  String address;
    private  int seatCount;
    private  int menuId;
    private  String imageURL;

    public Restaurant(int id, int brandId, String name, String address, int seatCount, int menuId, String imageURL) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.address = address;
        this.seatCount = seatCount;
        this.menuId = menuId;
        this.imageURL = imageURL;
    }

    public Restaurant() {
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



    public void setId(int id) {
        this.id = id;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getImageURL() {
        return imageURL;
    }
}
