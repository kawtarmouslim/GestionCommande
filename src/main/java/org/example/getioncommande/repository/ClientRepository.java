package org.example.getioncommande.repository;

import org.example.getioncommande.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findClientsByIdClient(Long idClient);
}
