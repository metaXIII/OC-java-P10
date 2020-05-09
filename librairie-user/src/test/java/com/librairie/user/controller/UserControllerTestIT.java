package com.librairie.user.controller;

import com.librairie.user.dto.SignInDto;
import com.librairie.user.exceptions.EmailExistsExceptionThrowable;
import com.librairie.user.exceptions.UsernameExistsExceptionThrowable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
@Transactional
class UserControllerTestIT {

    @Autowired
    private UserController userController;

    private SignInDto signInDto;

    @Test
    void signIn() throws EmailExistsExceptionThrowable, UsernameExistsExceptionThrowable, Exception {
        signInDto = new SignInDto();
        signInDto.setEmail("email@email");
        signInDto.setUsername("aze");
        signInDto.setPassword("password");
        assertDoesNotThrow(() -> userController.signIn(signInDto));
    }

    @Test
    void info() {
        assertDoesNotThrow(() -> userController.info());
    }

    @Test
    void getUser() {
        assertDoesNotThrow(() -> userController.getUser("azeaze"));
    }

    @Test
    void findUserById() {
        assertDoesNotThrow(() -> userController.findUserById(2));
    }
}