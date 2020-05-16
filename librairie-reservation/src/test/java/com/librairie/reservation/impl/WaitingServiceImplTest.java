package com.librairie.reservation.impl;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.WaitDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.model.Waiting;
import com.librairie.reservation.proxies.GatewayProxy;
import com.librairie.reservation.repositories.WaitingRepository;
import com.librairie.reservation.service.IReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WaitingServiceImplTest {

    @InjectMocks
    private WaitingServiceImpl waitingService;

    @Mock
    private WaitingRepository waitingRepository;

    @Mock
    private Reservation reservation;

    @Mock
    private WaitDto waitDto;

    @Mock
    private UserBean userBean;

    @Mock
    private GatewayProxy gatewayProxy;

    @Mock
    private Map<String, String> map;

    @Mock
    private IReservationService reservationService;

    @Test
    void getListOfWaitingByLivreId() {
        assertDoesNotThrow(() -> waitingService.getListOfWaitingByLivreId(1));
        assertEquals(Optional.empty(), waitingService.getListOfWaitingByLivreId(1));
    }

    @Test
    void insertWaitingforLivreId() {
        when(gatewayProxy.getUser(anyString())).thenReturn(mockUser());
        when(waitDto.getUser()).thenReturn(map);
        when(map.get(any())).thenReturn("aze");
        when(reservationService.getReservations(any())).thenReturn(new ArrayList<>());
        when(gatewayProxy.getMaxQuantityForBook(anyLong())).thenReturn(new ResponseEntity<>(10, HttpStatus.ACCEPTED));
        assertDoesNotThrow(() -> waitingService.insertWaitingForLivreId(waitDto));
    }

    @Test
    void getListOfWaitingByLivreIdWithNoProgress() {
        assertDoesNotThrow(() -> waitingService.getListOfWaitingByLivreIdWithNoProgress(1));
    }

    @Test
    void insertWaitingForLivreId() {
        assertDoesNotThrow(() -> waitingService.insertWaitingForLivreId(waitDto));
    }

    @Test
    void updateWaitingList() {
        assertDoesNotThrow(() -> waitingService.updateWaitingList(mockReservation()));
    }


    @Test
    void getAllWaitForNotification() {
        assertDoesNotThrow(() -> waitingService.getAllWaitForNotification());
    }

    @Test
    void updateWait() {
        assertDoesNotThrow(() -> waitingService.updateWait(1L));
    }

    @Test
    void getListOfWaitingByUserId() {
        when(gatewayProxy.getUser(anyString())).thenReturn(mockUser());
        assertDoesNotThrow(() -> waitingService.getListOfWaitingByUserId(mockUserBean()));
        assertEquals(202, waitingService.getListOfWaitingByUserId(mockUserBean()).getStatusCodeValue());
    }

    @Test
    void getPositionOfLivreId() {
        when(waitingRepository.findById(anyLong())).thenReturn(Optional.of(mockWait()));
        when(waitingRepository.findAllByLivreIdAndFinishedIsFalseOrderByDateReservation(anyLong())).thenReturn(Optional.of(new ArrayList<Waiting>() {{
            add(mockWait());
        }}));
        assertDoesNotThrow(() -> waitingService.getPositionOfLivreId(1L));
        assertEquals("1.1", waitingService.getPositionOfLivreId(1L).getBody());
        assertEquals(202, waitingService.getPositionOfLivreId(1L).getStatusCodeValue());
    }

    @Test
    void deleteByid() {
        assertDoesNotThrow(() -> waitingService.deleteByid(1L));
    }

    private Waiting mockWait() {
        Waiting waiting = new Waiting();
        waiting.setId(1);
        waiting.setUserId(1);
        return waiting;
    }

    private Reservation mockReservation() {
        Reservation reservation = new Reservation();
        reservation.setLivreId("1,1,1");
        return reservation;
    }

    private ResponseEntity<Optional<UserBean>> mockUser() {
        return new ResponseEntity<>(Optional.of(mockUserBean()), HttpStatus.ACCEPTED);
    }

    private UserBean mockUserBean() {
        UserBean userBean = new UserBean();
        userBean.setUsername("aze");
        userBean.setId(1);
        return userBean;
    }
}

