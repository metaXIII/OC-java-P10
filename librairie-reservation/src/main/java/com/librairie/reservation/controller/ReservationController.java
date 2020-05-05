package com.librairie.reservation.controller;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.service.IReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation/")
@Slf4j
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping(value = "reserve", consumes = "application/json")
    public ResponseEntity reserve(@RequestBody ReservDto data) {
        try {
            return new ResponseEntity<>(reservationService.reserve(data), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "reservations")
    public ResponseEntity reservations(@RequestBody UserBean user) {
        try {
            return new ResponseEntity<>(reservationService.getReservations(user), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("extend")
    public ResponseEntity extend(@RequestBody ReservationDto reservationDto) {
        return new ResponseEntity(reservationService.extendReservation(reservationDto));
    }

    @GetMapping("validate")
    public ResponseEntity<List<Reservation>> validate() {
        return new ResponseEntity<>(reservationService.getInvalidReservations(), HttpStatus.ACCEPTED);
    }
}
