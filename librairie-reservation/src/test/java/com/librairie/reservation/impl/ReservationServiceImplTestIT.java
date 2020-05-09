package com.librairie.reservation.impl;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ReservationServiceImplTestIT {

    @Autowired
    private ReservationServiceImpl reservationService;

    private ReservDto reservDto;

    private UserBean userBean;

    private ReservationDto reservationDto;

    @Test
    void reserve() {
        reservDto = new ReservDto();
        assertDoesNotThrow(() -> reservationService.reserve(reservDto));
    }

    @Test
    void getReservations() {
        assertDoesNotThrow(() -> reservationService.getReservations(userBean));
    }

    @Test
    void extendReservation() {
        reservationDto = new ReservationDto();
        reservationDto.setId(1);
        assertDoesNotThrow(() -> reservationService.extendReservation(reservationDto));
    }

    @Test
    void getInvalidReservations() {
        assertDoesNotThrow(() -> reservationService.getInvalidReservations());
    }
}