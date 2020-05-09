package com.librairie.batch.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void init() {
        user = new User();
        user.setEmail("aze");
        user.setUsername("aze");
    }

    @AfterEach
    public void end() {
        user = null;
    }

    @Test
    void setEmail() {
        assertDoesNotThrow(() -> user.setEmail("aze"));
    }

    @Test
    void setUsername() {
        assertDoesNotThrow(() -> user.setUsername("aze"));
    }

    @Test
    void getEmail() {
        assertEquals("aze", user.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals("aze", user.getUsername());
    }
}