package com.librairie.batch.controller;

import com.librairie.batch.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/batch/")
@Slf4j
public class SendEmail {

    private final EmailService emailService;

    public SendEmail(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("send")
    public void send() throws MessagingException {
        this.emailService.sendEmail();
    }
}
