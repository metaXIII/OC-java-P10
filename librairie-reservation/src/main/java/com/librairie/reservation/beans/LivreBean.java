package com.librairie.reservation.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LivreBean {
    private long id;

    private String nom;

    private String auteur;

    private Date dateParution;

    private String isbn;

    private String categorie;

    private String resume;

    private int quantite;

    private int maxQuantite;
}
