package com.librairie.librairie.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

class MaisonEditionTest {

    private MaisonEdition maisonEdition;

    @BeforeEach
    public void init() {
        maisonEdition = new MaisonEdition();
        maisonEdition.setId(1);
        maisonEdition.setNom("NOM");
        maisonEdition.setSiret("SIRET");
    }

    @AfterEach
    public void end() {
        maisonEdition = null;
    }

    @Test
    void getId() {
        assertEquals(1, maisonEdition.getId());
    }

    @Test
    void getNom() {
        assertEquals("NOM", maisonEdition.getNom());
    }

    @Test
    void getSiret() {
        assertEquals("SIRET", maisonEdition.getSiret());
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> maisonEdition.setId(anyInt()));
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> maisonEdition.setNom(anyString()));
    }

    @Test
    void setSiret() {
        assertDoesNotThrow(() -> maisonEdition.setSiret(anyString()));
    }

    @Test
    void testEquals() {
        assertEquals(true, maisonEdition.equals(maisonEdition));
    }

    @Test
    void canEqual() {
        assertEquals(true, maisonEdition.canEqual(maisonEdition));
    }

    @Test
    void testHashCode() {
        assertEquals(maisonEdition.hashCode(), maisonEdition.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("MaisonEdition(id=1, nom=NOM, siret=SIRET)", maisonEdition.toString());
    }
}