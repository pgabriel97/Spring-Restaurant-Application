package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();

    void insertReservation (Reservation reservation, String rest_id);

    void updateReservation(Reservation reservation);

    void executeUpdateReservation(Reservation reservation);

    public void deleteReservation(Reservation reservation);
}
