package com.librairie.librairie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Rollback
@Transactional
class LibrairieApplicationTestsIT {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> LibrairieApplication.main(new String[]{}));
    }

}
