package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.Reservation;

import java.text.ParseException;
import java.util.List;

public interface ReservationService {
    List<Reservation> findAll(String userId);

    void insertReservation (Reservation reservation, String rest_id, String user_id) throws ParseException;

    void updateReservation(Reservation reservation);

    void executeUpdateReservation(Reservation reservation);

    public void deleteReservation(Reservation reservation);
}
