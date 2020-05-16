package com.librairie.reservation.service;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.model.Waiting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    ResponseEntity reserve(ReservDto data);

    List<Reservation> getReservations(UserBean user);

    HttpStatus extendReservation(ReservationDto reservationDto);

    List<Reservation> getInvalidReservations();
}
