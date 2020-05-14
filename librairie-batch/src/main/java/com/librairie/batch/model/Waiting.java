package com.librairie.batch.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Waiting {
    private long id;

    private long livreId;

    private long userId;

    private LocalDate dateReservation;

    private LocalDate dateNotification;

    private boolean finished;
}
