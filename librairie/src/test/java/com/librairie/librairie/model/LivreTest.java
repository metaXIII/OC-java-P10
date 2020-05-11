package com.librairie.librairie.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LivreTest {

    private Livre livre;

    private MaisonEdition maisonEdition;

    @BeforeEach
    public void init() {
        livre = new Livre();
        livre.setId(1);
        livre.setNom("NOM");
        livre.setAuteur("AUTEUR");
        livre.setDateParution(new Date());
        livre.setIsbn("ISBN");
        livre.setCategorie("CAT");
        livre.setMaisonEdition(maisonEdition);
        livre.setResume("RESUME");
        livre.setQuantite(1);
        livre.setMaxQuantite(10);
    }

    @AfterEach
    public void end() {
        livre = null;
    }

    @Test
    void getId() {
        assertEquals(1, livre.getId());
    }

    @Test
    void getNom() {
        assertEquals("NOM", livre.getNom());
    }

    @Test
    void getAuteur() {
        assertEquals("AUTEUR", livre.getAuteur());
    }

    @Test
    void getDateParution() {
        int      annee;
        int      control;
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        annee = calendar.get(Calendar.YEAR);
        calendar.setTime(livre.getDateParution());
        control = calendar.get(Calendar.YEAR);
        assertEquals(annee, control);
    }

    @Test
    void getIsbn() {
        assertEquals("ISBN", livre.getIsbn());
    }

    @Test
    void getCategorie() {
        assertEquals("CAT", livre.getCategorie());
    }

    @Test
    void getMaisonEdition() {
        assertEquals(maisonEdition, livre.getMaisonEdition());
    }

    @Test
    void getResume() {
        assertEquals("RESUME", livre.getResume());
    }

    @Test
    void getQuantite() {
        assertEquals(1, livre.getQuantite());
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> livre.setId(1L));
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> livre.setNom("aze"));
    }

    @Test
    void setAuteur() {
        assertDoesNotThrow(() -> livre.setAuteur("aze"));
    }

    @Test
    void setDateParution() {
        assertDoesNotThrow(() -> livre.setDateParution(new Date()));
    }

    @Test
    void setIsbn() {
        assertDoesNotThrow(() -> livre.setIsbn("aze"));
    }

    @Test
    void setCategorie() {
        assertDoesNotThrow(() -> livre.setCategorie("aze"));
    }

    @Test
    void setMaisonEdition() {
        assertDoesNotThrow(() -> livre.setMaisonEdition(maisonEdition));
    }

    @Test
    void setResume() {
        assertDoesNotThrow(() -> livre.setResume("aze"));
    }

    @Test
    void setQuantite() {
        assertDoesNotThrow(() -> livre.setQuantite(1));
    }

    @Test
    void getMaxQuantite() {
        assertEquals(10, livre.getMaxQuantite());
    }

    @Test
    void setMaxQuantite() {
        assertDoesNotThrow(() -> livre.setMaxQuantite(1));
    }
}