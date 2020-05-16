package com.librairie.librairie.controller;

import com.librairie.librairie.dto.CollectionDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class LibrairieControllerTestIT {

    @Autowired
    private LibrairieController librairieController;

    private static CollectionDto collectionDto;

    @BeforeAll
    public static void init() {
        collectionDto = new CollectionDto("Harry potter à l'école des sorciers", "AUTEUR TEST", "EROTIQUE");
    }

    @Test
    void findAllLibrairie() {
        assertDoesNotThrow(() -> librairieController.findAllLibrairie());
        assertEquals(202, librairieController.findAllLibrairie().getStatusCodeValue());
    }

    @Test
    void find() {
        assertDoesNotThrow(() -> librairieController.find(collectionDto));
        assertEquals(202, librairieController.find(collectionDto).getStatusCodeValue());
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> librairieController.findById(1));
        assertEquals(202, librairieController.findById(1).getStatusCodeValue());
    }

    @Test
    void reserve() {
        assertEquals(202, librairieController.reserve(String.valueOf(1)).getStatusCodeValue());
    }

    @Test
    void getStock() {
        assertEquals(202, librairieController.getStock("1").getStatusCodeValue());
    }

    @Test
    void getStockWithNoExistingId() {
        assertThrows(NumberFormatException.class,
                     () -> librairieController.getStock("aze").getStatusCodeValue());
    }
}