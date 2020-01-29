package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Interface.ReservationDao;
import com.restaurant.Restaurant.Model.Reservation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Component
public class ReservationServiceImpl implements ReservationService{
    @Resource
    ReservationDao reservationDao;
    @Override
    public List<Reservation> findAll(String userId) {
        return reservationDao.findAll(userId);
    }
    @Override
    public void insertReservation(Reservation reservation, String rest_id, String user_id) throws ParseException {
        reservationDao.insertReservation(reservation, rest_id, user_id);

    }
    @Override
    public void updateReservation(Reservation reservation) {
        reservationDao.updateReservation(reservation);

    }

    @Override
    public void executeUpdateReservation(Reservation reservation) {
        reservationDao.executeReservation(reservation);

    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservationDao.deleteReservation(reservation);

    }
}
