package com.restaurant.Restaurant.Interface;

import com.restaurant.Restaurant.Model.Reservation;

import java.text.ParseException;
import java.util.List;

public interface ReservationDao {

    List<Reservation> findAll();

    void insertReservation(Reservation reservation, String rest_id, String user_id) throws ParseException;

    void updateReservation(Reservation reservation);

    void executeReservation(Reservation reservation);

    public void deleteReservation(Reservation reservation);

}
