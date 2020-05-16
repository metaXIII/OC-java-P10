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
import java.util.ArrayList;
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
        return waitingRepository.findAllByLivreIdAndFinishedIsFalseAndProgressIsFalseOrderByDateReservation(1);
    }

    @Override
    public ResponseEntity getListOfWaitingByLivreIdWithNoProgress(long id) {
        List<Waiting> waitingList = new ArrayList<>();
        if (waitingRepository.findAllByLivreIdAndFinishedIsFalseOrderByDateReservation(id).isPresent()) {
            waitingList = waitingRepository.findAllByLivreIdAndFinishedIsFalseOrderByDateReservation(id).get();
        }
        return new ResponseEntity(waitingList, HttpStatus.ACCEPTED);
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
                    if (tab < stock * 2) {
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
        Optional<Waiting> waiting = waitingRepository.findByUserIdAndLivreId(user.getId(), data.getLivreId());
        return waiting.isEmpty();
    }

    private void insertWaiting(long id, long userId) {
        Waiting waiting = new Waiting();
        waiting.setLivreId(id);
        waiting.setUserId(userId);
        waiting.setDateReservation(LocalDate.now());
        waiting.setDateNotification(null);
        waiting.setFinished(false);
        waiting.setProgress(false);
        waitingRepository.save(waiting);
    }

    @Override
    public void updateWaitingList(Reservation reservation) {
        Optional<Waiting> wait;
        String[]          reservationIds = reservation.getLivreId().split(",");
        for (String element : reservationIds) {
            wait = waitingRepository.findByLivreIdAndUserIdAndFinishedIsFalse(Long.parseLong(element),
                                                                              reservation.getUserId());
            if (wait.isPresent()) {
                wait.get().setFinished(true);
                waitingRepository.save(wait.get());
            }
        }
    }

    @Override
    public List<Waiting> getAllWaitForNotification() {
        return waitingRepository.findAllByDateNotificationIsNotNullAndFinishedIsFalse();
    }

    @Override
    public ResponseEntity updateWait(Long id) {
        Optional<Waiting> control = waitingRepository.findById(id);
        control.ifPresent(el -> {
            if (!el.isFinished() && LocalDate.now().isAfter(el.getDateNotification().plusDays(2))) {
                el.setFinished(true);
                Optional<List<Waiting>> waitingList =
                        waitingRepository.findAllByLivreIdAndFinishedIsFalseAndProgressIsFalseOrderByDateReservation(el.getLivreId());
                waitingList.ifPresent(list -> {
                    Waiting waiting = list.get(0);
                    waiting.setProgress(true);
                    waiting.setDateNotification(LocalDate.now());
                    waitingRepository.save(waiting);
                });
            }
            waitingRepository.save(el);
        });
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity getListOfWaitingByUserId(UserBean userBean) {
        ResponseEntity<Optional<UserBean>> control = gatewayProxy.getUser(userBean.getUsername());
        if (control.getBody().isPresent())
            return new ResponseEntity(waitingRepository.findAllByUserIdAndFinishedIsFalseOrderByDateReservation(userBean.getId()), HttpStatus.ACCEPTED);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getPositionOfLivreId(Long id) {
        int               position = 0;
        Optional<Waiting> waiting  = waitingRepository.findById(id);
        Optional<List<Waiting>> waitingList =
                waitingRepository.findAllByLivreIdAndFinishedIsFalseOrderByDateReservation(waiting.get().getLivreId());
        if (waiting.isPresent()) {
            if (waitingList.isPresent()) {
                while ((waiting.get().getId() != waitingList.get().get(position).getId()) && position < waitingList.get().size() - 1) {
                    position++;
                }
            }
        }
        String resp = (position + 1) + "." + waitingList.get().size();
        return new ResponseEntity(resp, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity deleteByid(long id) {
        Optional<Waiting> waiting = waitingRepository.findById(id);
        if (waiting.isPresent()) {
            waiting.get().setFinished(true);
            waitingRepository.save(waiting.get());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
