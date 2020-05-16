package com.librairie.reservation.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTest {

    private Waiting waiting;

    @BeforeEach
    public void init() {
        waiting = new Waiting();
        waiting.setId(1);
        waiting.setUserId(1);
        waiting.setDateNotification(LocalDate.now());
        waiting.setDateReservation(LocalDate.now());
        waiting.setFinished(true);
        waiting.setLivreId(1);
    }

    @AfterEach
    public void end() {
        waiting = null;
    }

    @Test
    void getId() {
        assertEquals(1, waiting.getId());
    }

    @Test
    void getLivreId() {
        assertEquals(1, waiting.getLivreId());
    }

    @Test
    void getUserId() {
        assertEquals(1, waiting.getUserId());
    }

    @Test
    void getDateReservation() {
        assertNotNull(waiting.getDateReservation());
    }

    @Test
    void getDateLimite() {
        assertNotNull(waiting.getDateNotification());
    }

    @Test
    void isFinished() {
        assertTrue(waiting.isFinished());
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> waiting.setId(2));
    }

    @Test
    void setLivreId() {
        assertDoesNotThrow(() -> waiting.setLivreId(2));
    }

    @Test
    void setUserId() {
        assertDoesNotThrow(() -> waiting.setUserId(2));
    }

    @Test
    void setDateReservation() {
        assertDoesNotThrow(() -> waiting.setDateReservation(LocalDate.now()));
    }

    @Test
    void setDateLimite() {
        assertDoesNotThrow(() -> waiting.setDateNotification(LocalDate.now()));
    }

    @Test
    void setFinished() {
        assertDoesNotThrow(() -> waiting.setFinished(false));
    }
}