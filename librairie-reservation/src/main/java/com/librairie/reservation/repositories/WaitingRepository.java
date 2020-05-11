package com.librairie.reservation.repositories;

import com.librairie.reservation.model.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    Optional<List<Waiting>> findAllByLivreId(long id);
}
