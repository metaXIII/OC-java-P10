package com.librairie.reservation.repositories;

import com.librairie.reservation.model.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    Optional<List<Waiting>> findAllByLivreIdAndFinishedIsFalseAndProgressIsFalseOrderByDateReservation(long id);

    Optional<List<Waiting>> findAllByLivreIdAndFinishedIsFalseAndProgressIsTrueOrderByDateReservation(long id);

    List<Waiting> findAllByUserIdAndFinishedIsFalseOrderByDateReservation(long id);

    Optional<List<Waiting>> findAllByLivreIdAndFinishedIsFalseOrderByDateReservation(long id);

    Optional<Waiting> findByLivreIdAndUserIdAndFinishedIsFalse(long livreId, long userId);

    List<Waiting> findAllByDateNotificationIsNotNullAndFinishedIsFalse();

    Optional<Waiting> findByUserIdAndLivreId(long userId, long livreId);
}
