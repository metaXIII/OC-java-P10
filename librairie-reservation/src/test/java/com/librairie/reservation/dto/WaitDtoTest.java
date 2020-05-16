package com.librairie.reservation.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WaitDtoTest {

    @InjectMocks
    private WaitDto waitDto;

    @BeforeEach
    public void init() {
        waitDto = new WaitDto();
        waitDto.setLivreId(1);
        waitDto.setUser(new HashMap<>());
    }

    @AfterEach
    public void end() {
        waitDto = null;
    }


    @Test
    void getLivreId() {
        assertEquals(1, waitDto.getLivreId());
    }

    @Test
    void getUser() {
        assertNotNull(waitDto.getUser());
    }

    @Test
    void setLivreId() {
        assertDoesNotThrow(() -> waitDto.setLivreId(1L));
    }

    @Test
    void setUser() {
        assertDoesNotThrow(() -> waitDto.setUser(new HashMap<>()));
    }
}