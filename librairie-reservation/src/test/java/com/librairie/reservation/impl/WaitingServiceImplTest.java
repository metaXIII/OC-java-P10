package com.librairie.reservation.impl;

import com.librairie.reservation.repositories.WaitingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WaitingServiceImplTest {

    @InjectMocks
    private WaitingServiceImpl waitingService;

    @Mock
    private WaitingRepository waitingRepository;

    @Test
    void getListOfWaitingByLivreId() {
        assertDoesNotThrow(() -> waitingService.getListOfWaitingByLivreId(1));
        assertEquals(Optional.empty(), waitingService.getListOfWaitingByLivreId(1));
    }

    @Test
    void insertWaitingforLivreId() {
        assertDoesNotThrow(() -> waitingService.insertWaitingforLivreId(1, 1));
    }
}