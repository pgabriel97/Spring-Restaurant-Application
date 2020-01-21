package com.restaurant.Restaurant.Model;

import java.util.UUID;

public class Franchise {

    private final int id;
    private final String name;
    private final String type;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Franchise(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
