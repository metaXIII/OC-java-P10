package com.librairie.reservation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReservDtoTest {

    private ReservDto reservDto;

    @BeforeEach
    public void init() {
        reservDto = new ReservDto();
        reservDto.setCollection(new ArrayList<>());
        reservDto.setUser(new HashMap<>());
    }

    @Test
    void setCollection() {
        assertDoesNotThrow(() -> reservDto.setCollection(new ArrayList<>()));
    }

    @Test
    void setUser() {
        assertDoesNotThrow(() -> reservDto.setUser(new HashMap<>()));
    }

    @Test
    void getCollection() {
        assertNotNull(reservDto.getCollection());
    }

    @Test
    void getUser() {
        assertNotNull(reservDto.getUser());
    }
}