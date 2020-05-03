package com.librairie.librairie.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

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
        assertDoesNotThrow(() -> livre.setId(anyLong()));
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> livre.setNom(anyString()));
    }

    @Test
    void setAuteur() {
        assertDoesNotThrow(() -> livre.setAuteur(anyString()));
    }

    @Test
    void setDateParution() {
        assertDoesNotThrow(() -> livre.setDateParution(any(Date.class)));
    }

    @Test
    void setIsbn() {
        assertDoesNotThrow(() -> livre.setIsbn(anyString()));
    }

    @Test
    void setCategorie() {
        assertDoesNotThrow(() -> livre.setCategorie(anyString()));
    }

    @Test
    void setMaisonEdition() {
        assertDoesNotThrow(() -> livre.setMaisonEdition(any(MaisonEdition.class)));
    }

    @Test
    void setResume() {
        assertDoesNotThrow(() -> livre.setResume(anyString()));
    }

    @Test
    void setQuantite() {
        assertDoesNotThrow(() -> livre.setQuantite(anyInt()));
    }

    @Test
    void testEquals() {
        assertEquals(true, livre.equals(livre));
    }

    @Test
    void canEqual() {
        assertEquals(true, livre.canEqual(livre));
    }

    @Test
    void testHashCode() {
        assertEquals(livre.hashCode(), livre.hashCode());
    }

    @Test
    void testToString() {
        String eq = "Livre(id=1, nom=NOM, auteur=AUTEUR, dateParution=" + livre.getDateParution() +
            ", isbn=ISBN, categorie=CAT, maisonEdition=null, resume=RESUME, quantite=1)";
        assertEquals(eq, livre.toString());
    }
}