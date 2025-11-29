package org.example.getioncommande.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private int idCommandeLigne;
    private int quantite;
    private double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)
    @JsonBackReference
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "idProduit", nullable = false)
    private Produit produit;

}
