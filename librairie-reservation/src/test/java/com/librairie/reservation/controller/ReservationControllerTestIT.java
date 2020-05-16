package com.librairie.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import com.librairie.reservation.dto.WaitDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureMockMvc
class ReservationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationController reservationService;

    private ReservDto reservDto;

    private WaitDto waitDto;

    private ReservationDto reservationDto;

    private UserBean userBean;


    @Test
    void reserve() {
        assertDoesNotThrow(() -> reservationService.reserve(reservDto));
    }

    @Test
    void reserveIt() throws Exception {
        this.mockMvc.perform(post("/api/reservation/reserve").content(asJsonString(reservDto))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void reservations() {
        userBean = new UserBean();
        userBean.setId(1);
        assertDoesNotThrow(() -> reservationService.reservations(userBean));
    }

    @Test
    void reservationsIt() throws Exception {
        this.mockMvc.perform(post("/api/reservation/reservations").content(asJsonString(new UserBean()))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void extend() {
        reservationDto = new ReservationDto();
        reservationDto.setId(1);
        assertDoesNotThrow(() -> reservationService.extend(reservationDto));
    }

    @Test
    void extendIt() throws Exception {
        this.mockMvc.perform(put("/api/reservation/extend").content(asJsonString(reservDto))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void validate() {
        assertDoesNotThrow(() -> reservationService.validate());
    }

    @Test
    void validateIT() throws Exception {
        this.mockMvc.perform(get("/api/reservation/validate"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void addToWaitListIt() throws Exception {
        waitDto = new WaitDto();
        Map<String, String > map = new HashMap<>();
        waitDto.setLivreId(1);
        waitDto.setUser(map);
        this.mockMvc.perform(post("/api/reservation/waiting").content(asJsonString(waitDto))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void NotificationsListIT() throws Exception {
        this.mockMvc.perform(get("/api/reservation/getAllWait"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void updateWaitIT() throws Exception {
        this.mockMvc.perform(get("/api/reservation/updateWait/{id}", 1)
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void getListOfWaitingByLivreIdWithNoProgressIT() throws Exception {
        this.mockMvc.perform(get("/api/reservation/getWaitForLivreId/{id}", 1)
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void getPositionForLivreId() throws Exception {
        this.mockMvc.perform(get("/api/reservation/position/{id}", 1)
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void getListOfWaitingByUserId() throws Exception {
        userBean = new UserBean();
        this.mockMvc.perform(post("/api/reservation/getAllWaitForUser")
                                     .content(asJsonString(userBean))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void deleteByIdIt() throws Exception {
        this.mockMvc.perform(put("/api/reservation/deleteById")
                                     .content(asJsonString(1))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
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