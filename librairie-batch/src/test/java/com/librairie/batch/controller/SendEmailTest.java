package com.librairie.batch.controller;

import com.librairie.batch.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SendEmailTest {
    @InjectMocks
    private SendEmail sendEmail;

    @Autowired
    private EmailService emailService;

    @Test
    void SendEmail() {
        assertDoesNotThrow(() -> new SendEmail(emailService));
    }

    @Test
    void send() {
        assertThrows(NullPointerException.class, () -> sendEmail.send());
    }
}