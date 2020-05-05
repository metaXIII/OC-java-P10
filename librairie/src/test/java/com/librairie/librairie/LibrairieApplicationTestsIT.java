package com.librairie.librairie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LibrairieApplicationTestsIT {

    @Test
    void contextLoads() {
        LibrairieApplication.main(new String[]{});
    }

}
