package com.librairie.reservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Rollback
@Transactional
class ReservationApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> ReservationApplication.main(new String[]{}));
    }

}
