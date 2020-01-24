package com.restaurant.Restaurant.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "id_restaurant")
    private String id_restaurant;

    @Column(name = "id_user")
    private String id_user;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "start_time")
    private String start_time;

    @Column(name = "guest_number")
    private String guest_number;

    public void setId_restaurant(String id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setGuest_number(String guest_number) {
        this.guest_number = guest_number;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getId_restaurant() {
        return id_restaurant;
    }

    public String getId_user() {
        return id_user;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getGuest_number() {
        return guest_number;
    }
}
