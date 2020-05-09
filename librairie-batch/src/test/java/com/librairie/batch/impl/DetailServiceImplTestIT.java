package com.librairie.batch.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Rollback
@Transactional
class DetailServiceImplTestIT {

    @Autowired
    private DetailServiceImpl detailService;

    @Test
    void getReservations() {
        assertNull(detailService.getReservations());
    }

    @Test
    void getUser() {
        assertEquals(Optional.empty(), detailService.getUser(1));
    }

    @Test
    void getLivreById() {
        assertNull(detailService.getLivreById(1));
    }
}