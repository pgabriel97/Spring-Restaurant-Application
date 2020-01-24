package com.restaurant.Restaurant.Interface;

import com.restaurant.Restaurant.Model.Reservation;

import java.util.List;

public interface ReservationDao {

    List<Reservation> findAll();

    void insertReservation(Reservation reservation, String rest_id);

    void updateReservation(Reservation reservation);

    void executeReservation(Reservation reservation);

    public void deleteReservation(Reservation reservation);

}
