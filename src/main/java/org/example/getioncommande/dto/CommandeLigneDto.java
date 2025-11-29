package org.example.getioncommande.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.getioncommande.entites.CommandeLigne}
 */
@Value
public class CommandeLigneDto implements Serializable {
    int idCommandeLigne;
    int quantite;
    double prixUnitaire;
    int commandeIdCommande;
    int produitIdProduit;
}