package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Interface.ReservationDao;
import com.restaurant.Restaurant.Model.Reservation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReservationServiceImpl implements ReservationService{
    @Resource
    ReservationDao reservationDao;
    @Override
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }
    @Override
    public void insertReservation(Reservation reservation, String rest_id) {
        reservationDao.insertReservation(reservation, rest_id);

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
