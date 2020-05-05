package com.librairie.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Rollback
class ConfigServerApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> ConfigServerApplication.main(new String[]{}));
    }
}
