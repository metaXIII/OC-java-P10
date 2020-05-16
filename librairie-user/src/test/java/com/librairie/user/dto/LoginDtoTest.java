package com.librairie.user.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoginDtoTest {

    @InjectMocks
    private LoginDto loginDto;

    @BeforeEach
    public void init() {
        loginDto = new LoginDto();
        loginDto.setUsername("aze");
        loginDto.setPassword("aze");
    }

    @AfterEach
    public void end() {
        loginDto = null;
    }

    @Test
    void getUsername() {
        assertEquals("aze", loginDto.getUsername());
    }

    @Test
    void setUsername() {
        assertDoesNotThrow(() -> loginDto.setUsername("aze2"));
    }

    @Test
    void getPassword() {
        assertEquals("aze", loginDto.getPassword());
    }

    @Test
    void setPassword() {
        assertDoesNotThrow(() -> loginDto.setPassword("azeaze"));
    }
}