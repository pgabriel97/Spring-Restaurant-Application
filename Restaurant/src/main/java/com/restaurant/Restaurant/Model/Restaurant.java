package com.restaurant.Restaurant.Model;

import java.util.UUID;

public class Restaurant {

    private final int id;
    private final String name;
    private final String address;
    private final String type;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public Restaurant(int id, String name, String address, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }
}
