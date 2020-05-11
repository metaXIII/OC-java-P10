package com.librairie.reservation.service;

import com.librairie.reservation.model.Waiting;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IWaitingService {
    Optional<List<Waiting>> getListOfWaitingByLivreId(long id);

    ResponseEntity<Boolean> insertWaitingforLivreId(long id, long userId);
}
