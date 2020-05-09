package com.librairie.reservation.impl;

import com.librairie.reservation.beans.LivreBean;
import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.ReservDto;
import com.librairie.reservation.dto.ReservationDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.proxies.GatewayProxy;
import com.librairie.reservation.repositories.ReservationRepository;
import com.librairie.reservation.service.IReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private GatewayProxy gatewayProxy;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ResponseEntity reserve(ReservDto data) {
        Reservation   reservation   = new Reservation();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Optional<UserBean> user = gatewayProxy.getUser(data.getUser().get("username")).getBody();
            if (Objects.requireNonNull(user).isPresent()) {
                try {
                    for (LivreBean livre : data.getCollection()) {
                        if (Objects.requireNonNull(gatewayProxy.checkStock(String.valueOf(livre.getId())).getBody()).isPresent())
                            stringBuilder.append(stringBuilder.toString().isEmpty() ? "" : ",").append(livre.getId());
                    }
                    makeReservation(reservation, stringBuilder, user);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    private void makeReservation(Reservation reservation, StringBuilder stringBuilder, Optional<UserBean> user) {
        reservation.setLivreId(stringBuilder.toString());
        reservation.setUserId(user.get().getId());
        reservation.setDateReservation(LocalDate.now());
        reservation.setExtended(false);
        reservation.setFinished(false);
        reservation.setDateLimite(reservation.getDateReservation().plusWeeks(4));
        if (!stringBuilder.toString().isEmpty())
            reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservations(UserBean userBean) {
        try {
            Optional<UserBean> user = gatewayProxy.getUser(userBean.getUsername()).getBody();
            if (Objects.requireNonNull(user).isPresent()) {
                return reservationRepository.findAllByUserIdAndFinishedIsFalseOrderByIdDesc(user.get().getId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public HttpStatus extendReservation(ReservationDto reservationDto) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationDto.getId());
        //BugFixe
        if (reservation.isPresent() && !reservation.get().isExtended()) {
            reservation.get().setExtended(true);
            reservation.get().setDateLimite(reservation.get().getDateLimite().plusWeeks(4));
            reservationRepository.save(reservation.get());
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public List<Reservation> getInvalidReservations() {
        LocalDate         localDate = LocalDate.now();
        List<Reservation> result    = new ArrayList<>();
        extendReservations(localDate);
        List<Reservation> controlReservation = reservationRepository.findAllByFinishedIsFalseAndExtendedIsTrue();
        controlReservation.forEach(reservation -> {
            if (reservation.getDateLimite().isBefore(localDate))
                result.add(reservation);
        });
        return result;
    }

    private void extendReservations(LocalDate localDate) {
        List<Reservation> update = reservationRepository.findAllByFinishedIsFalseAndExtendedIsFalse();
        update.forEach(reservation -> {
            if (reservation.getDateLimite().isBefore(localDate)) {
                reservation.setExtended(true);
                reservation.setDateLimite(reservation.getDateLimite().plusDays(28));
                reservationRepository.save(reservation);
            }
        });
    }
}
