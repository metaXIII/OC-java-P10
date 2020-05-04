package com.librairie.librairie.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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
    void testEquals() {
        assertEquals(true, collectionDto.equals(collectionDto));
    }

    @Test
    void canEqual() {
        assertEquals(true, collectionDto.canEqual(collectionDto));
    }

    @Test
    void testHashCode() {
        CollectionDto collectionDtoBis = new CollectionDto(collectionDto.getNom(),collectionDto.getAuteur(),
                                                           collectionDto.getCategorie());
        assertEquals(collectionDtoBis.hashCode(), collectionDto.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("CollectionDto(nom=NOM, auteur=AUTEUR, categorie=CAT)", collectionDto.toString());
    }

    @Test
    void CollectionDto(){
        assertDoesNotThrow((ThrowingSupplier<CollectionDto>) CollectionDto::new);
    }
}