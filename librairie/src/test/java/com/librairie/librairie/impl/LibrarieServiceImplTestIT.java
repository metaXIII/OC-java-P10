package com.librairie.librairie.impl;

import com.librairie.librairie.dto.CollectionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class LibrarieServiceImplTestIT {

    @Autowired
    private LibrarieServiceImpl librairieService;

    @Test
    void findAll() {
        assertNotNull(librairieService.findAll());
    }

    @Test
    void find() {
        assertNotNull(librairieService.find(new CollectionDto("","","")));
    }

    @Test
    void findById() {
        assertNotNull(librairieService.findById(1));
    }

    @Test
    void reserve() {
        assertDoesNotThrow(() -> librairieService.reserve("1"));
    }

    @Test
    void getStock() {
        assertNotNull(librairieService.getStock("1"));
        assertEquals(202, librairieService.getStock("1").getStatusCodeValue());
    }

    @Test
    void getStockWithError() {
        assertNotNull(librairieService.getStock("0"));
        assertEquals(400, librairieService.getStock("0").getStatusCodeValue());
    }
}