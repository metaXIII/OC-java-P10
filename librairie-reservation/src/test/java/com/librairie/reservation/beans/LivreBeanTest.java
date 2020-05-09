package com.librairie.reservation.beans;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LivreBeanTest {

    private LivreBean livreBean;

    @BeforeEach
    public void init(){
        livreBean = new LivreBean();
        livreBean.setId(1);
        livreBean.setNom("nom");
        livreBean.setAuteur("auteur");
        livreBean.setDateParution(new Date());
        livreBean.setIsbn("isbn");
        livreBean.setCategorie("cat");
        livreBean.setResume("resume");
        livreBean.setQuantite(1);
    }

    @AfterEach
    public void end() {
        livreBean = null;
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> livreBean.setId(1));
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> livreBean.setNom("aze"));
    }

    @Test
    void setAuteur() {
        assertDoesNotThrow(() -> livreBean.setAuteur("aze"));
    }

    @Test
    void setDateParution() {
        assertDoesNotThrow(() -> livreBean.setDateParution(new Date()));
    }

    @Test
    void setIsbn() {
        assertDoesNotThrow(() -> livreBean.setIsbn("aze"));
    }

    @Test
    void setCategorie() {
        assertDoesNotThrow(() -> livreBean.setCategorie("cat2"));
    }

    @Test
    void setResume() {
        assertDoesNotThrow(() -> livreBean.setResume("aze"));
    }

    @Test
    void setQuantite() {
        assertDoesNotThrow(() -> livreBean.setQuantite(10));
    }

    @Test
    void getId() {
        assertEquals(1, livreBean.getId());
    }

    @Test
    void getNom() {
        assertEquals("nom", livreBean.getNom());
    }

    @Test
    void getAuteur() {
        assertEquals("auteur", livreBean.getAuteur());
    }

    @Test
    void getDateParution() {
        assertNotNull(livreBean.getDateParution());
    }

    @Test
    void getIsbn() {
        assertEquals("isbn", livreBean.getIsbn());
    }

    @Test
    void getCategorie() {
        assertEquals("cat", livreBean.getCategorie());
    }

    @Test
    void getResume() {
        assertEquals("resume", livreBean.getResume());
    }

    @Test
    void getQuantite() {
        assertEquals(1, livreBean.getQuantite());
    }
}