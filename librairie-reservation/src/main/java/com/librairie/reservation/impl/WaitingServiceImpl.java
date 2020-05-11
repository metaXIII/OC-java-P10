package com.librairie.reservation.impl;

import com.librairie.reservation.model.Waiting;
import com.librairie.reservation.proxies.GatewayProxy;
import com.librairie.reservation.repositories.WaitingRepository;
import com.librairie.reservation.service.IWaitingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class WaitingServiceImpl implements IWaitingService {

    @Autowired
    private WaitingRepository waitingRepository;

    @Autowired
    private GatewayProxy gatewayProxy;

    @Override
    public Optional<List<Waiting>> getListOfWaitingByLivreId(long id) {
        return waitingRepository.findAllByLivreId(1);
    }

    @Override
    public ResponseEntity<Boolean> insertWaitingforLivreId(long id, long userId) {
        int tab   = 0;
        int stock = 0;
        try {
            stock = gatewayProxy.getMaxQuantityForBook(id).getBody();
        } catch (NullPointerException e) {
            log.error("Erreur de connection");
        }
        if (this.getListOfWaitingByLivreId(id).isPresent())
            tab = this.getListOfWaitingByLivreId(id).get().size();
        if (tab < stock) {
            insertWaiting(id, userId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
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
