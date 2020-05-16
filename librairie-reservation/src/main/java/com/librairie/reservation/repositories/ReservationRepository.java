package com.librairie.reservation.repositories;

import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.model.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserIdAndFinishedIsFalseOrderByIdDesc(long id);

    List<Reservation> findAllByFinishedIsFalseAndExtendedIsTrue();

    List<Reservation> findAllByFinishedIsFalseAndExtendedIsFalse();

    Optional<List<Waiting>> findAllByLivreId(long id);
}
