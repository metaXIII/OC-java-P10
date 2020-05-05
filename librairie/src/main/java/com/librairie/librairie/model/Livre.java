package com.librairie.librairie.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Livre {
    @Id
    @GeneratedValue
    private long id;

    private String nom;

    private String auteur;

    @Column(name = "DATEPARUTION")
    private Date dateParution;

    private String isbn;

    private String categorie;

    @ManyToOne()
    @JoinColumn(name = "MAISONEDITION")
    private MaisonEdition maisonEdition;

    private String resume;

    private int quantite;
}
