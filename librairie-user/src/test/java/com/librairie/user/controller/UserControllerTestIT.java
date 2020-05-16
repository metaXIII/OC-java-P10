package com.librairie.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librairie.user.dto.SignInDto;
import com.librairie.user.exceptions.EmailExistsExceptionThrowable;
import com.librairie.user.exceptions.UsernameExistsExceptionThrowable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Rollback
@Transactional
@AutoConfigureMockMvc
class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

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
    void SignInIt() throws Exception {
        this.mockMvc.perform(post("/api/user/signIn").content(asJsonString(signInDto))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void info() {
        assertDoesNotThrow(() -> userController.info());
    }

    @Test
    void infoIT() throws Exception {
        this.mockMvc.perform(get("/api/user/info"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void getUser() {
        assertDoesNotThrow(() -> userController.getUser("azeaze"));
    }

    @Test
    void getUserIT() throws Exception {
        this.mockMvc.perform(get("/api/user/me/{name}", "aze"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void findUserById() {
        assertDoesNotThrow(() -> userController.findUserById(2));
    }

    @Test
    void finfUserByIdIT() throws Exception {
        this.mockMvc.perform(get("/api/user/get/{id}", 1))
                .andDo(print()).andExpect(status().isAccepted());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}