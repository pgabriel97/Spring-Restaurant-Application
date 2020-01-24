package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Reservation;
import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Service.ReservationService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {
    @Resource
    ReservationService reservationService;

    @GetMapping(value = "/reservationList")
    public List<Reservation> getReservation() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/reservation/{restaurantID}")
    public String getreservation() {
        return "reservation";
    }

    @PostMapping("/reservation/{restaurantID}")
    public String createReservation(Reservation reservation, @PathVariable String restaurantID) {
        reservationService.insertReservation(reservation,restaurantID);
        return "redirect:/";
    }
}

