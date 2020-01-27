package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.Reservation;
import com.restaurant.Restaurant.Model.Restaurant;
import com.restaurant.Restaurant.Service.ReservationService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
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

    @RequestMapping(value = "/reservation/{restaurantId}")
    public String getreservation( @PathVariable String restId) {
        return "reservation";
    }

    @GetMapping("/makeReservation")
    public String createReservation( Reservation reservation,  @RequestParam("restaurant_id") String restaurantId, @RequestParam("user_id") String userId, BindingResult bindingResult) throws ParseException {
        reservationService.insertReservation(reservation, restaurantId, userId);
        return "redirect:/";
    }
}

