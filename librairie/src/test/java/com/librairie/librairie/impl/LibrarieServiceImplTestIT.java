package com.librairie.librairie.impl;

import com.librairie.librairie.dto.CollectionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}