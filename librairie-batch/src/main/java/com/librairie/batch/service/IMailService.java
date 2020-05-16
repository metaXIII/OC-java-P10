package com.librairie.batch.service;

import com.librairie.batch.model.Livre;
import com.librairie.batch.model.Reservation;
import com.librairie.batch.model.User;
import com.librairie.batch.model.Waiting;
import org.springframework.http.ResponseEntity;

import java.net.CacheRequest;
import java.util.List;
import java.util.Optional;

public interface IMailService {
    ResponseEntity<List<Reservation>> getReservations();

    Optional<User> getUser(long userId);

    Livre getLivreById(long id);

    ResponseEntity<List<Waiting>> getWaiting();

    ResponseEntity setDateLimite(Waiting wait);
}
