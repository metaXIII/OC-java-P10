package com.librairie.reservation.controller;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import com.librairie.reservation.dto.WaitDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.model.Waiting;
import com.librairie.reservation.service.IReservationService;
import com.librairie.reservation.service.IWaitingService;
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

    @Autowired
    private IWaitingService waitingService;

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

    @PostMapping(value = "waiting", consumes = "application/json")
    public ResponseEntity addToWaitList(@RequestBody WaitDto waitDto) {
        return new ResponseEntity(waitingService.insertWaitingForLivreId(waitDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("getAllWait")
    public ResponseEntity<List<Waiting>> NotificationsList() {
        return new ResponseEntity<>(waitingService.getAllWaitForNotification(), HttpStatus.ACCEPTED);
    }

    @GetMapping("updateWait/{id}")
    public ResponseEntity updateWait(@PathVariable("id") Long id) {
        return waitingService.updateWait(id);
    }
}
