package com.librairie.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Rollback
class BatchApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> BatchApplication.main(new String[]{}));
    }
}
