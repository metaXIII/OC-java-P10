package com.librairie.librairie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librairie.librairie.dto.CollectionDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@Rollback
class LibrairieControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LibrairieController librairieController;

    private static CollectionDto collectionDto;

    @BeforeAll
    public static void init() {
        collectionDto = new CollectionDto("Harry potter à l'école des sorciers", "AUTEUR TEST", "EROTIQUE");
    }

    @Test
    void findAllLibrairie() {
        assertDoesNotThrow(() -> librairieController.findAllLibrairie());
        assertEquals(202, librairieController.findAllLibrairie().getStatusCodeValue());
    }

    @Test
    void find() {
        assertDoesNotThrow(() -> librairieController.find(collectionDto));
        assertEquals(202, librairieController.find(collectionDto).getStatusCodeValue());
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> librairieController.findById(1));
        assertEquals(202, librairieController.findById(1).getStatusCodeValue());
    }

    @Test
    void reserve() {
        assertEquals(202, librairieController.reserve(String.valueOf(1)).getStatusCodeValue());
    }

    @Test
    void getStock() {
        assertEquals(202, librairieController.getStock("1").getStatusCodeValue());
    }

    @Test
    void getStockWithNoExistingId() {
        assertThrows(NumberFormatException.class,
                     () -> librairieController.getStock("aze").getStatusCodeValue());
    }


    @Test
    public void findAllLibrairieIT() throws Exception {
        this.mockMvc.perform(get("/api/librairie/findAll")).andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void findIT() throws Exception {
        this.mockMvc.perform(post("/api/librairie/find").content(asJsonString(new CollectionDto("", "", "")))
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void findbyIdIT() throws Exception {
        this.mockMvc.perform(get("/api/librairie/findById/1"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void reservedIT() throws Exception {
        this.mockMvc.perform(get("/api/librairie/reserve/1"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void getStockIT() throws Exception {
        this.mockMvc.perform(get("/api/librairie/getStock/1"))
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