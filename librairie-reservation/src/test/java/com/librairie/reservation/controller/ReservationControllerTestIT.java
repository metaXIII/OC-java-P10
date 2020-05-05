package com.librairie.reservation.controller;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Transactional
@Rollback
class ReservationControllerTestIT {

    @Autowired
    private ReservationController reservationService;

    private ReservDto reservDto;

    private ReservationDto reservationDto;

    private UserBean userBean;

    @Test
    void reserve() {
        assertDoesNotThrow(() -> reservationService.reserve(reservDto));
    }

    @Test
    void reservations() {
        userBean = new UserBean();
        userBean.setId(1);
        assertDoesNotThrow(() -> reservationService.reservations(userBean));
    }

    @Test
    void extend() {
        reservationDto = new ReservationDto();
        reservationDto.setId(1);
        assertDoesNotThrow(() -> reservationService.extend(reservationDto));
    }

    @Test
    void validate() {
        assertDoesNotThrow(() -> reservationService.validate());
    }
}