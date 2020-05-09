package com.librairie.user.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PasswordConfigTest {

    @InjectMocks
    private PasswordConfig passwordConfig;

    @Test
    void passwordEncoder() {
        assertDoesNotThrow(() -> passwordConfig.passwordEncoder());
        assertNotNull(passwordConfig.passwordEncoder());
    }
}