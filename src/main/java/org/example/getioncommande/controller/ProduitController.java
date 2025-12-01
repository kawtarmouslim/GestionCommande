package org.example.getioncommande.controller;

import org.example.getioncommande.dto.ProduitDto;
import org.example.getioncommande.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProduitController {
    @Autowired
    private  ProduitService produitService;
    @PostMapping("produit")
    public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produit) {
        ProduitDto produitDto=produitService.create(produit);
        return ResponseEntity.ok().body(produitDto);
    }
    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAllProduits() {
        List<ProduitDto>produitDtoList=produitService.listAll();
        return ResponseEntity.ok().body(produitDtoList);

    }
    @PutMapping("updateproduit/{idProduit}")
    public ResponseEntity<ProduitDto>updateProduit(@RequestBody ProduitDto produit, @PathVariable Long idProduit) {
        ProduitDto p=produitService.update(idProduit,produit);
        return ResponseEntity.ok().body(p);

    }
    @DeleteMapping("delete/{idProduit}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long idProduit) {
        produitService.delete(idProduit);
        return ResponseEntity.ok().build();

    }

}
