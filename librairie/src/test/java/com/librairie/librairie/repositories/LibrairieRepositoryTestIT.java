package com.librairie.librairie.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Transactional
@Rollback
class LibrairieRepositoryTestIT {

    @Autowired
    private LibrairieRepository librairieRepository;

    @Test
    void findByNomContainingIgnoreCaseAndNomContainingIgnoreCaseAndCategorieContainingIgnoreCase() {
        assertDoesNotThrow(() -> librairieRepository.findByNomContainingIgnoreCaseAndNomContainingIgnoreCaseAndCategorieContainingIgnoreCase("", "", ""));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> librairieRepository.findById(1));
    }
}