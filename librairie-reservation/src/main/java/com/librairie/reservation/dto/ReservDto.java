package com.librairie.reservation.dto;

import com.librairie.reservation.beans.LivreBean;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ReservDto implements Serializable {
    private List<LivreBean>     collection;
    private Map<String, String> user;
}
