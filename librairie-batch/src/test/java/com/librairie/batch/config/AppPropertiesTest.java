package com.librairie.batch.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AppPropertiesTest {

    @InjectMocks
    private AppProperties appProperties;

    @Test
    void getDefaultEmailSender() {
        assertEquals(null, appProperties.getDefaultEmailSender());
    }

    @Test
    void setDefaultEmailSender() {
        assertDoesNotThrow(() -> appProperties.setDefaultEmailSender("aze"));
    }
}