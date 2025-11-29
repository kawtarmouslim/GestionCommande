package org.example.getioncommande.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;
    private String nomProduit;
    private double prix;
    private int stock;
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CommandeLigne> lignes;
}
