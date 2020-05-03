package com.librairie.librairie.impl;

import com.librairie.librairie.dto.CollectionDto;
import com.librairie.librairie.model.Livre;
import com.librairie.librairie.repositories.LibrairieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibrarieServiceImplTest {

    @InjectMocks
    private LibrarieServiceImpl librairieService;

    @Mock
    private LibrairieRepository librairieRepository;

    private CollectionDto collectionDto;

    @Test
    void findAll() {
        assertDoesNotThrow(() -> librairieService.findAll());
    }

    @Test
    void find() {
        collectionDto = new CollectionDto("", "", "");
        assertDoesNotThrow(() -> librairieService.find(collectionDto));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> librairieService.findById(1));
    }

    @Test
    void reserve() {
        assertDoesNotThrow(() -> librairieService.reserve("1"));
    }

    @Test
    void shouldReturnConflictStatusWhenQuantiteIsNotEnoughForReserve() {
        when(librairieRepository.findById(anyLong())).thenReturn(Optional.of(mockLivreWithBadQuantity()));
        assertEquals(409, librairieService.reserve("1").getStatusCodeValue());
    }

    private Livre mockLivre() {
        Livre livre = new Livre();
        livre.setQuantite(1);
        return livre;
    }

    private Livre mockLivreWithBadQuantity() {
        Livre livre = new Livre();
        livre.setQuantite(0);
        return livre;
    }
}