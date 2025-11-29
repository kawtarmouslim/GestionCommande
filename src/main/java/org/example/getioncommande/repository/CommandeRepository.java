package org.example.getioncommande.repository;

import org.example.getioncommande.entites.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
