package com.librairie.reservation.service;

import com.librairie.reservation.beans.UserBean;
import com.librairie.reservation.dto.WaitDto;
import com.librairie.reservation.model.Reservation;
import com.librairie.reservation.model.Waiting;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IWaitingService {
    Optional<List<Waiting>> getListOfWaitingByLivreId(long id);

    ResponseEntity getListOfWaitingByLivreIdWithNoProgress(long id);

    ResponseEntity<Boolean> insertWaitingForLivreId(WaitDto data);

    void updateWaitingList(Reservation reservation);

    List<Waiting> getAllWaitForNotification();

    ResponseEntity updateWait(Long id);

    ResponseEntity getListOfWaitingByUserId(UserBean userBean);

    ResponseEntity getPositionOfLivreId(Long id);

    ResponseEntity deleteByid(long id);
}
