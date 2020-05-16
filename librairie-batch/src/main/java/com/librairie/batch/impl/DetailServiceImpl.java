package com.librairie.batch.impl;

import com.librairie.batch.model.Livre;
import com.librairie.batch.model.Reservation;
import com.librairie.batch.model.User;
import com.librairie.batch.model.Waiting;
import com.librairie.batch.proxies.GatewayProxy;
import com.librairie.batch.service.IMailService;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class DetailServiceImpl implements IMailService {
    @Autowired
    private GatewayProxy gatewayProxy;

    @Override
    public ResponseEntity<List<Reservation>> getReservations() {
        try {
            return gatewayProxy.getInvalidReservations();
        } catch (RetryableException retryableException) {
            log.error(retryableException.getMessage());
            return null;
        }
    }

    @Override
    public Optional<User> getUser(long userId) {
        try {
            return gatewayProxy.getUserWithId(userId);
        } catch (RetryableException retryableException) {
            log.error(retryableException.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Livre getLivreById(long id) {
        try {
            return Objects.requireNonNull(gatewayProxy.getLivreById(id).getBody()).orElse(new Livre());
        } catch (Exception retryableException) {
            log.error(retryableException.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Waiting>> getWaiting() {
        try {
            return gatewayProxy.getNotificationList();
        } catch (RetryableException retryableException) {
            log.error(retryableException.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity setDateLimite(Waiting wait) {
        try {
            return gatewayProxy.updateWait(wait.getId());
        } catch (RetryableException retryableException) {
            log.error(retryableException.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_GATEWAY);
    }
}
