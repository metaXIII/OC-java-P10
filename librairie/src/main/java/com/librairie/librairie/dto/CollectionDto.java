package com.librairie.librairie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {
    private String nom;
    private String auteur;
    private String categorie;
}
