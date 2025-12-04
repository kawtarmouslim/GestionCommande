package org.example.getioncommande.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.getioncommande.dto.CommandeDto;
import org.example.getioncommande.dto.CommandeLigneDto;
import org.example.getioncommande.entites.Client;
import org.example.getioncommande.entites.Commande;
import org.example.getioncommande.entites.CommandeLigne;
import org.example.getioncommande.entites.Produit;
import org.example.getioncommande.repository.ClientRepository;
import org.example.getioncommande.repository.CommandeRepository;
import org.example.getioncommande.repository.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

@Service
public class CommandeService {

    private  CommandeRepository commandeRepository;
    private  ClientRepository clientRepository;
    private  ProduitRepository produitRepository;
    private  ModelMapper modelMapper;

    public CommandeService(CommandeRepository commandeRepository, ClientRepository clientRepository, ProduitRepository produitRepository, ModelMapper modelMapper) {
        this.commandeRepository = commandeRepository;
        this.clientRepository = clientRepository;
        this.produitRepository = produitRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public CommandeDto createCommande(CommandeDto commandeDto) {

        // 1. Récupérer et valider le client
        Client client = clientRepository.findById(commandeDto.getClientIdClient())
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID : " + commandeDto.getClientIdClient()));

        // 2. Créer l'entité Commande
        Commande commande = new Commande();
        commande.setDateCommande(commandeDto.getDateCommande() != null ? commandeDto.getDateCommande() : new Date(System.currentTimeMillis()));
        commande.setStatus(commandeDto.getStatus() != null ? commandeDto.getStatus() : "EN_COURS");
        commande.setClient(client);
        commande.setLignes(new ArrayList<>()); // Important : initialiser la liste

        // 3. Traiter chaque ligne du DTO
        double total = 0.0;

        for (CommandeLigneDto ligneDto : commandeDto.getLignes()) {

            // Récupérer le produit
            Produit produit = produitRepository.findById(ligneDto.getProduitIdProduit())
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + ligneDto.getProduitIdProduit()));

            // Créer la ligne de commande
            CommandeLigne ligne = new CommandeLigne();
            ligne.setQuantite(ligneDto.getQuantite());
            ligne.setPrixUnitaire(ligneDto.getPrixUnitaire());
            ligne.setProduit(produit);
            ligne.setCommande(commande); // Relation bidirectionnelle

            // Ajouter la ligne à la commande
            commande.getLignes().add(ligne);

            // Cumuler le total
            total += ligneDto.getQuantite() * ligneDto.getPrixUnitaire();
        }

        // 4. Affecter le total calculé
        commande.setTotal(total);

        // 5. Sauvegarder en base (cascade ALL va persister les lignes aussi)
        Commande savedCommande = commandeRepository.save(commande);

        // 6. Retourner le DTO mis à jour avec l'ID généré et le total calculé
        return convertToDto(savedCommande);
    }

    // Méthode utilitaire de conversion Entité → DTO (propre et sans boucle infinie)
    private CommandeDto convertToDto(Commande commande) {
        CommandeDto dto = new CommandeDto();
        dto.setIdCommande(commande.getIdCommande());
        dto.setDateCommande(commande.getDateCommande());
        dto.setStatus(commande.getStatus());
        dto.setTotal(commande.getTotal());
        dto.setClientIdClient(commande.getClient().getIdClient());

        List<CommandeLigneDto> ligneDtos = commande.getLignes().stream()
                .map(this::convertLigneToDto)
                .toList();

        dto.setLignes(ligneDtos);
        return dto;
    }

    private CommandeLigneDto convertLigneToDto(CommandeLigne ligne) {
        CommandeLigneDto dto = new CommandeLigneDto();
        dto.setIdCommandeLigne(ligne.getIdCommandeLigne());
        dto.setQuantite(ligne.getQuantite());
        dto.setPrixUnitaire(ligne.getPrixUnitaire());
        dto.setCommandeIdCommande(ligne.getCommande().getIdCommande());
        dto.setProduitIdProduit(ligne.getProduit().getIdProduit());
        return dto;
    }
}