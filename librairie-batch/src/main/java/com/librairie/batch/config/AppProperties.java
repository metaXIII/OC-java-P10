package com.librairie.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "app")
@Component
@Validated
public class AppProperties {

    @Email
    private String defaultEmailSender;

    public String getDefaultEmailSender() {
        return this.defaultEmailSender;
    }

    public void setDefaultEmailSender(String defaultEmailSender) {
        this.defaultEmailSender = defaultEmailSender;
    }

}