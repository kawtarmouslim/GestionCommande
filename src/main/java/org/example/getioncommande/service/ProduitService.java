package org.example.getioncommande.service;

import org.example.getioncommande.dto.ProduitDto;
import org.example.getioncommande.entites.Produit;
import org.example.getioncommande.repository.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {
    private ProduitRepository produitRepository;
    private ModelMapper modelMapper;

    public ProduitService(ProduitRepository produitRepository, ModelMapper modelMapper) {
        this.produitRepository = produitRepository;
        this.modelMapper = modelMapper;
    }

    public ProduitDto create(ProduitDto produitDto) {
        Produit produit=modelMapper.map(produitDto, Produit.class);
        Produit savedProduit=produitRepository.save(produit);
        return modelMapper.map(savedProduit,ProduitDto.class);
    }

    public List<ProduitDto>listAll(){
        List<Produit>produitList=produitRepository.findAll();
        return produitList
                .stream()
                .map(produit->modelMapper.map(produit,ProduitDto.class))
                .collect(Collectors.toList());
    }

    public ProduitDto getById(Long id){
        Produit produit=produitRepository.findById(id).orElse(null);
        return modelMapper.map(produit,ProduitDto.class);


    }
    public ProduitDto update(Long id, ProduitDto produitDto) {
        Produit produit=produitRepository.findById(id).orElse(null);
        produit.setNomProduit(produitDto.getNomProduit());
        produit.setPrix(produitDto.getPrix());
        produit.setStock(produitDto.getStock());
        Produit savedProduit=produitRepository.save(produit);
        return modelMapper.map(savedProduit,ProduitDto.class);
    }
    public void delete(Long id) {
        produitRepository.deleteById(id);


    }
}
