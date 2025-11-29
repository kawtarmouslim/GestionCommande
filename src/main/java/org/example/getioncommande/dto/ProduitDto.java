package org.example.getioncommande.dto;

import lombok.Value;
import org.example.getioncommande.entites.Produit;

import java.io.Serializable;

/**
 * DTO for {@link Produit}
 */
@Value
public class ProduitDto implements Serializable {
    int idProduit;
    String nomProduit;
    double prix;
    int stock;
}