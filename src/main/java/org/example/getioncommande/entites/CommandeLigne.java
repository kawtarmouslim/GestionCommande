package org.example.getioncommande.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeLigne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommandeLigne;

    private int quantite;
    private double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)

    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "idProduit", nullable = false)
    private Produit produit;
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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }



}
