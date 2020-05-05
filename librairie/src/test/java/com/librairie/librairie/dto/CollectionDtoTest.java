package com.librairie.librairie.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionDtoTest {

    private CollectionDto collectionDto;

    @BeforeEach
    public void init() {
        collectionDto = new CollectionDto("NOM", "AUTEUR", "CAT");
    }

    @AfterEach
    public void end() {
        collectionDto = null;
    }

    @Test
    void getNom() {
        assertEquals("NOM", collectionDto.getNom());
    }

    @Test
    void getAuteur() {
        assertEquals("AUTEUR", collectionDto.getAuteur());
    }

    @Test
    void getCategorie() {
        assertEquals("CAT", collectionDto.getCategorie());
    }

    @Test
    void setNom() {
        assertDoesNotThrow(() -> collectionDto.setNom("aze"));
    }

    @Test
    void setAuteur() {
        assertDoesNotThrow(() -> collectionDto.setAuteur("aze"));
    }

    @Test
    void setCategorie() {
        assertDoesNotThrow(() -> collectionDto.setCategorie("aze"));
    }

    @Test
    void Constructor() {
        assertDoesNotThrow((ThrowingSupplier<CollectionDto>) CollectionDto::new);
    }

    @Test
    void ConstructorWithArgs() {
        assertDoesNotThrow(() -> new CollectionDto("", "", ""));
    }
}