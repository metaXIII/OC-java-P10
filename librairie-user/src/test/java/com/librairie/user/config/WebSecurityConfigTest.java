package com.librairie.user.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WebSecurityConfigTest {

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    private HttpSecurity httpSecurity;

    @Test
    void configure() throws Exception {
        assertThrows(NullPointerException.class, () -> webSecurityConfig.configure(httpSecurity));
    }

    @Test
    void authProvider() {
        assertThrows(IllegalArgumentException.class, () -> webSecurityConfig.authProvider());
    }
}