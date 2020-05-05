package com.librairie.batch.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@Rollback
@Transactional
class SendEmailTestIT {

    @Autowired
    private SendEmail sendEmail;

    @Test
    void send() {
        assertDoesNotThrow(() -> sendEmail.send());
    }
}