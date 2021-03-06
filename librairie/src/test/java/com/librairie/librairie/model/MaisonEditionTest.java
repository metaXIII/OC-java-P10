package com.librairie.librairie.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertDoesNotThrow(() -> maisonEdition.setId(1));
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> maisonEdition.setNom("test"));
    }

    @Test
    void setSiret() {
        assertDoesNotThrow(() -> maisonEdition.setSiret("test"));
    }
}