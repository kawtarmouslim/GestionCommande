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
        Client client = clientRepository.findById(commandeDto.getClientIdClient())
                .orElseThrow(() -> new RuntimeException(
                        "Client non trouvé avec ID : " + commandeDto.getClientIdClient()));

        // 🔹 Mapper DTO → Entité
        Commande commande = modelMapper.map(commandeDto, Commande.class);
        commande.setClient(client);
        commande.setDateCommande(
                commandeDto.getDateCommande() != null ?
                        commandeDto.getDateCommande() : new Date(System.currentTimeMillis())
        );
        commande.setStatus(commande.getStatus() != null ? commande.getStatus() : "EN_COURS");
        commande.setLignes(new ArrayList<>());

        double total = 0.0;

        // 🔹 Conversion des lignes
        for (CommandeLigneDto ligneDto : commandeDto.getLignes()) {

            Produit produit = produitRepository.findById(ligneDto.getProduitIdProduit())
                    .orElseThrow(() -> new RuntimeException(
                            "Produit non trouvé avec ID : " + ligneDto.getProduitIdProduit()));

            CommandeLigne ligne = modelMapper.map(ligneDto, CommandeLigne.class);

            ligne.setProduit(produit);
            ligne.setCommande(commande);
            commande.getLignes().add(ligne);

            total += ligneDto.getQuantite() * ligneDto.getPrixUnitaire();
        }

        commande.setTotal(total);

        // 🔥 Sauvegarde en cascade
        Commande saved = commandeRepository.save(commande);

        // 🔄 Entité → DTO via ModelMapper
        return modelMapper.map(saved, CommandeDto.class);

    }
}