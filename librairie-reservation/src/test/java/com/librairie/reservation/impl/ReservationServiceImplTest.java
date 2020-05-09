package com.librairie.reservation.impl;

import com.librairie.reservation.beans.LivreBean;
import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import com.librairie.reservation.proxies.GatewayProxy;
import com.librairie.reservation.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @InjectMocks
    ReservationServiceImpl reservationService;

    @Mock
    private ReservDto reservDto;

    @Mock
    private UserBean userBean;

    @Mock
    private ReservationDto reservationDto;

    @Mock
    private GatewayProxy gatewayProxy;

    @Mock
    private ResponseEntity<Optional<UserBean>> responseEntity;

    @Mock
    private Map<String, String> map;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    void reserve() {
        when(reservDto.getUser()).thenReturn(map);
        when(map.get("username")).thenReturn("aze");
        when(gatewayProxy.getUser((any()))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(Optional.of(mockUser()));
        when(reservDto.getCollection()).thenReturn(mockCollection());
        when(gatewayProxy.checkStock("1")).thenReturn(mockStock());
        assertDoesNotThrow(() -> reservationService.reserve(reservDto));
    }

    @Test
    void getReservations() {
        userBean = mockUser();
        when(gatewayProxy.getUser(anyString())).thenReturn(mockUserOptional());
        assertDoesNotThrow(() -> reservationService.getReservations(userBean));
    }

    @Test
    void extendReservation() {
        reservationDto = new ReservationDto();
        reservationDto.setId(1);
        assertDoesNotThrow(() -> reservationService.extendReservation(reservationDto));
    }

    @Test
    void getInvalidReservations() {
        assertDoesNotThrow(() -> reservationService.getInvalidReservations());
    }

    private UserBean mockUser() {
        UserBean userBean = new UserBean();
        userBean.setId(1);
        userBean.setUsername("aze");
        return userBean;
    }

    private ResponseEntity<Optional<UserBean>> mockUserOptional() {
        return new ResponseEntity<>(Optional.of(mockUser()), HttpStatus.ACCEPTED);
    }

    private List<LivreBean> mockCollection() {
        List<LivreBean> livreBeans = new ArrayList<>();
        LivreBean       livreBean  = new LivreBean();
        livreBean.setId(1);
        livreBeans.add(livreBean);
        return livreBeans;
    }

    private ResponseEntity<Optional<Boolean>> mockStock() {
        Optional<Boolean> optional = Optional.of(true);
        return new ResponseEntity<Optional<Boolean>>(optional, HttpStatus.ACCEPTED);
    }
}
