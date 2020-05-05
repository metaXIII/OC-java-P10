package com.librairie.librairie.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "MAISONEDITION")
public class MaisonEdition {
    @Id
    @GeneratedValue
    private int id;

    private String nom;

    private String siret;
}
