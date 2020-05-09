package com.librairie.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {
    private long id;

    private String livreId;

    private LocalDate dateReservation;

    private LocalDate dateLimite;

    private boolean extended;

    private boolean finished;
}
