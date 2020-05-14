package com.librairie.librairie.service;

import com.librairie.librairie.dto.CollectionDto;
import com.librairie.librairie.model.Livre;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ILibrairieService {
    List<Livre> findAll();

    List<Livre> find(CollectionDto collectionDto);

    ResponseEntity<Optional<Livre>> findById(long id);

    ResponseEntity<Boolean> reserve(String id);

    ResponseEntity<Integer> getStock(String id);
}
