package com.librairie.batch.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MailConfigTest {

    @InjectMocks
    private MailConfig mailConfig;

    @Test
    void getJavaMailSender() {
        assertDoesNotThrow(() -> mailConfig.getJavaMailSender());
        assertNotNull(mailConfig.getJavaMailSender());
    }
}