package org.example.getioncommande.dto;

import lombok.Value;

import java.io.Serializable;


public class CommandeLigneDto implements Serializable {
    Long idCommandeLigne;
    int quantite;
    double prixUnitaire;
    Long commandeIdCommande;
    Long produitIdProduit;

    public Long getIdCommandeLigne() {
        return idCommandeLigne;
    }

    public void setIdCommandeLigne(Long idCommandeLigne) {
        this.idCommandeLigne = idCommandeLigne;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Long getCommandeIdCommande() {
        return commandeIdCommande;
    }

    public void setCommandeIdCommande(Long commandeIdCommande) {
        this.commandeIdCommande = commandeIdCommande;
    }

    public Long getProduitIdProduit() {
        return produitIdProduit;
    }

    public void setProduitIdProduit(Long produitIdProduit) {
        this.produitIdProduit = produitIdProduit;
    }
}