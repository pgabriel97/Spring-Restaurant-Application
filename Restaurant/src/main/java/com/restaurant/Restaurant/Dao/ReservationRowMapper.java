package com.restaurant.Restaurant.Dao;

import com.restaurant.Restaurant.Model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet rs, int arg1) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId_restaurant(rs.getString("id_restaurant"));
        reservation.setId_user(rs.getString("id_user"));
        reservation.setStart_date(rs.getString("start_date"));
        reservation.setStart_time(rs.getString("start_time"));
        reservation.setGuest_number(rs.getString("guest_number"));
        return reservation;
    }
}
