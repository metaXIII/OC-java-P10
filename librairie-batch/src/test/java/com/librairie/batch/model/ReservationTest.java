package com.librairie.batch.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;

    @BeforeEach
    public void init() {
        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setLivreId("1");
        reservation.setDateReservation(LocalDate.now().minusDays(2));
        reservation.setDateLimite(LocalDate.now());
        reservation.setExtended(true);
        reservation.setFinished(true);
        reservation.setUserId(1);
    }


    @AfterEach
    public void end() {
        reservation = null;
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> reservation.setId(1));
    }

    @Test
    void setLivreId() {
        assertDoesNotThrow(() -> reservation.setLivreId("1"));
    }

    @Test
    void setDateReservation() {
        assertDoesNotThrow(() -> reservation.setDateReservation(LocalDate.now()));
    }

    @Test
    void setDateLimite() {
        assertDoesNotThrow(() -> reservation.setDateLimite(LocalDate.now()));
    }

    @Test
    void setExtended() {
        assertDoesNotThrow(() -> reservation.setExtended(true));
    }

    @Test
    void setFinished() {
        assertDoesNotThrow(() -> reservation.setFinished(true));
    }

    @Test
    void setUserId() {
        assertDoesNotThrow(() -> reservation.setUserId(1));
    }

    @Test
    void getId() {
        assertEquals(1, reservation.getId());
    }

    @Test
    void getLivreId() {
        assertEquals("1", reservation.getLivreId());
    }

    @Test
    void getDateReservation() {
        assertThat(reservation.getDateReservation()).isInstanceOf(LocalDate.class);
    }

    @Test
    void getDateLimite() {
        assertThat(reservation.getDateLimite()).isInstanceOf(LocalDate.class);
    }

    @Test
    void isExtended() {
        assertTrue(reservation.isExtended());
    }

    @Test
    void isFinished() {
        assertTrue(reservation.isFinished());
    }

    @Test
    void getUserId() {
        assertEquals(1, reservation.getUserId());
    }
}