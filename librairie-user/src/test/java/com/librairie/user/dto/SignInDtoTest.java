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
class SignInDtoTest {

    @InjectMocks
    private SignInDto signInDto;

    @BeforeEach
    public void init() {
        signInDto = new SignInDto();
        signInDto.setUsername("aze");
        signInDto.setEmail("aze@aze");
        signInDto.setPassword("aze");
    }

    @AfterEach
    public void end() {
        signInDto = null;
    }

    @Test
    void getUsername() {
        assertEquals("aze", signInDto.getUsername());
    }

    @Test
    void setUsername() {
        assertDoesNotThrow(() -> signInDto.setUsername("aze2"));
    }

    @Test
    void getPassword() {
        assertEquals("aze", signInDto.getPassword());
    }

    @Test
    void setPassword() {
        assertDoesNotThrow(() -> signInDto.setPassword("aaaaa"));
    }

    @Test
    void getEmail() {
        assertEquals("aze@aze", signInDto.getEmail());
    }

    @Test
    void setEmail() {
        //toImprove
        assertDoesNotThrow(() -> signInDto.setEmail("aze@aze"));
    }
}