package com.librairie.batch.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LivreTest {

    private Livre livre;

    @BeforeEach
    public void init() {
        livre = new Livre();
        livre.setNom("NOM");
        livre.setAuteur("AUTEUR");
    }

    @AfterEach
    public void end() {
        livre = null;
    }

    @Test
    void setAuteur() {
        assertDoesNotThrow(() -> livre.setAuteur("aze"));
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> livre.setNom("aze"));
    }

    @Test
    void getAuteur() {
        assertEquals("AUTEUR", livre.getAuteur());
    }

    @Test
    void getNom() {
        assertEquals("NOM", livre.getNom());
    }
}