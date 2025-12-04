package org.example.getioncommande.controller;

import org.example.getioncommande.dto.CommandeDto;
import org.example.getioncommande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/V1/commande")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @PostMapping
    public ResponseEntity<CommandeDto> createCommande(@RequestBody CommandeDto commandeDto) {
        return ResponseEntity.ok(commandeService.createCommande(commandeDto));
    }
}
