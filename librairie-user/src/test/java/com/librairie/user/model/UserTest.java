package com.librairie.user.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void init() {
        user = new User();
        user.setId(1);
        user.setUsername("aze");
        user.setEmail("aze@aze");
        user.setPassword("aze");
    }

    @AfterEach
    public void end() {
        user = null;
    }

    @Test
    void getId() {
        assertEquals(1, user.getId());
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> user.setId(10));
    }

    @Test
    void getUsername() {
        assertEquals("aze", user.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        assertDoesNotThrow(() -> user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertDoesNotThrow(() -> user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertDoesNotThrow(() -> user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertDoesNotThrow(() -> user.isEnabled());
    }

    @Test
    void setUsername() {
        assertDoesNotThrow(() -> user.setUsername("azeaze"));
    }

    @Test
    void getAuthorities() {
        assertDoesNotThrow(() -> user.getAuthorities());
    }

    @Test
    void getPassword() {
        assertEquals("aze", user.getPassword());
    }

    @Test
    void setPassword() {
        assertDoesNotThrow(() -> user.setPassword("azeazeazeaze"));
    }

    @Test
    void getEmail() {
        assertEquals("aze@aze", user.getEmail());
    }

    @Test
    void setEmail() {
        //Could be improve
        assertDoesNotThrow(() -> user.setEmail("aze@aze"));
    }
}