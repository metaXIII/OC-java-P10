package com.librairie.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class WaitDto {
    private long                livreId;
    private Map<String, String> user;
}
