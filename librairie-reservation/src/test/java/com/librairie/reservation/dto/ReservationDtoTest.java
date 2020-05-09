package com.librairie.reservation.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDtoTest {

    private ReservationDto reservationDto;

    @BeforeEach
    public void init() {
        reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setLivreId("1");
        reservationDto.setDateReservation(LocalDate.now());
        reservationDto.setDateLimite(LocalDate.now());
        reservationDto.setExtended(true);
        reservationDto.setFinished(true);
    }

    @AfterEach
    public void end() {
        reservationDto = null;
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> reservationDto.setId(1));
    }

    @Test
    void setLivreId() {
        assertDoesNotThrow(() -> reservationDto.setLivreId("1"));
    }

    @Test
    void setDateReservation() {
        assertDoesNotThrow(() -> reservationDto.setDateReservation(LocalDate.now()));
    }

    @Test
    void setDateLimite() {
        assertDoesNotThrow(() -> reservationDto.setDateLimite(LocalDate.now()));
    }

    @Test
    void setExtended() {
        assertDoesNotThrow(() -> reservationDto.setExtended(true));
    }

    @Test
    void setFinished() {
        assertDoesNotThrow(() -> reservationDto.setFinished(true));
    }

    @Test
    void getId() {
        assertEquals(1, reservationDto.getId());
    }

    @Test
    void getLivreId() {
        assertEquals("1", reservationDto.getLivreId());
    }

    @Test
    void getDateReservation() {
        assertNotNull(reservationDto.getDateReservation());
    }

    @Test
    void getDateLimite() {
        assertNotNull(reservationDto.getDateLimite());
    }

    @Test
    void isExtended() {
        assertTrue(reservationDto.isExtended());
    }

    @Test
    void isFinished() {
        assertTrue(reservationDto.isFinished());
    }
}