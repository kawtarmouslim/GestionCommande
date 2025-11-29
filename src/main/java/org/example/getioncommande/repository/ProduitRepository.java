package org.example.getioncommande.repository;

import org.example.getioncommande.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
