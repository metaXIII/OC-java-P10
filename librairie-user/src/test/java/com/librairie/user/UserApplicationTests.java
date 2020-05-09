package com.librairie.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Rollback
@Transactional
public class UserApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> UserApplication.main(new String[]{}));
    }
}
