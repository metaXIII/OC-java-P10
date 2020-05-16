package com.librairie.batch.impl;

import com.librairie.batch.model.Livre;
import com.librairie.batch.model.User;
import com.librairie.batch.model.Waiting;
import com.librairie.batch.proxies.GatewayProxy;
import feign.RetryableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetailServiceImplTest {

    @InjectMocks
    private DetailServiceImpl detailService;

    @Mock
    private GatewayProxy gatewayProxy;

    @Test
    void getReservations() {
        when(gatewayProxy.getInvalidReservations()).thenReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
        assertDoesNotThrow(() -> detailService.getReservations());
    }

    @Test
    void getReservationsWithException() {
        when(gatewayProxy.getInvalidReservations()).thenThrow(RetryableException.class);
        assertDoesNotThrow(() -> detailService.getReservations());
    }

    @Test
    void getUser() {
        when(gatewayProxy.getUserWithId(anyLong())).thenReturn(Optional.of(new User()));
        assertDoesNotThrow(() -> detailService.getUser(1));
    }

    @Test
    void getUserWithException() {
        when(gatewayProxy.getUserWithId(anyLong())).thenThrow(RetryableException.class);
        assertDoesNotThrow(() -> detailService.getUser(1));
    }

    @Test
    void getLivreById() {
        when(gatewayProxy.getLivreById(anyLong())).thenReturn(new ResponseEntity<>(Optional.of(new Livre()),
                                                                                   HttpStatus.ACCEPTED));
        assertDoesNotThrow(() -> detailService.getLivreById(1));
    }

    @Test
    void getLivreByIdWithException() {
        when(gatewayProxy.getLivreById(anyLong())).thenThrow(RetryableException.class);
        assertDoesNotThrow(() -> detailService.getLivreById(1));
    }

    @Test
    void getWaiting() {
        when(gatewayProxy.getNotificationList()).thenReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
        assertDoesNotThrow(() -> detailService.getWaiting());
    }

    @Test
    void getWaitingWithException() {
        when(gatewayProxy.getNotificationList()).thenThrow(RetryableException.class);
        assertNull(detailService.getWaiting());
    }

    @Test
    void setDateLimite() {
        assertDoesNotThrow(() -> detailService.setDateLimite(new Waiting()));
    }
    @Test
    void setDateLimiteWithException() {
        when(gatewayProxy.updateWait(anyLong())).thenThrow(RetryableException.class);
        assertDoesNotThrow(() -> detailService.setDateLimite(new Waiting()));
    }
}