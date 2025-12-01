package org.example.getioncommande.dto;

import lombok.Value;
import org.example.getioncommande.entites.Produit;

import java.io.Serializable;


public class ProduitDto implements Serializable {
    Long idProduit;
    String nomProduit;
    double prix;
    int stock;

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}