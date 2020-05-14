package com.librairie.reservation.impl;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.WaitDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.model.Waiting;
import com.librairie.reservation.proxies.GatewayProxy;
import com.librairie.reservation.repositories.WaitingRepository;
import com.librairie.reservation.service.IReservationService;
import com.librairie.reservation.service.IWaitingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class WaitingServiceImpl implements IWaitingService {

    @Autowired
    private WaitingRepository waitingRepository;

    @Autowired
    private GatewayProxy gatewayProxy;

    @Autowired
    private IReservationService reservationService;

    @Override
    public Optional<List<Waiting>> getListOfWaitingByLivreId(long id) {
        return waitingRepository.findAllByLivreId(1);
    }

    @Override
    public ResponseEntity insertWaitingForLivreId(WaitDto data) {
        try {
            //Déclaration variables
            int stock = 0;
            int tab   = 0;
            //Vérfication si l'user existe
            Optional<UserBean> user = gatewayProxy.getUser(data.getUser().get("username")).getBody();
            if (Objects.requireNonNull(user).isPresent()) {
                //Vérification de l'user s'il n'a pas déjà un emprunt pour le livre demandé
                if (this.canWait(user.get(), data)) {
                    try {
                        stock = gatewayProxy.getMaxQuantityForBook(data.getLivreId()).getBody();
                    } catch (NullPointerException e) {
                        log.error("Erreur de connection");
                    }
                    if (this.getListOfWaitingByLivreId(data.getLivreId()).isPresent())
                        tab = this.getListOfWaitingByLivreId(data.getLivreId()).get().size();
                    if (tab < stock) {
                        this.insertWaiting(data.getLivreId(), user.get().getId());
                        return new ResponseEntity<>(HttpStatus.ACCEPTED);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    private boolean canWait(UserBean user, WaitDto data) {
        List<Reservation> reservations = reservationService.getReservations(user);
        for (Reservation reservation : reservations) {
            String[] reservationIds = reservation.getLivreId().split(",");
            for (String element : reservationIds) {
                if ((data.getLivreId() == Long.parseLong(element))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void insertWaiting(long id, long userId) {
        Waiting waiting = new Waiting();
        waiting.setId(1);
        waiting.setLivreId(id);
        waiting.setUserId(userId);
        waiting.setDateReservation(LocalDate.now());
        waiting.setDateLimite(LocalDate.now().plusDays(2));
        waiting.setFinished(false);
        waitingRepository.save(waiting);
    }
}
