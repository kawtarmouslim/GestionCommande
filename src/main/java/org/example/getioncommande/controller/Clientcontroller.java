package org.example.getioncommande.controller;

import org.example.getioncommande.dto.ClientDto;
import org.example.getioncommande.entites.Client;
import org.example.getioncommande.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class Clientcontroller {
    @Autowired
    private  ClientService clientService;
    @PostMapping("create")
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
        ClientDto clientDto1=clientService.createClient( clientDto);
        return ResponseEntity.ok(clientDto1);

    }
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        List<ClientDto> clientDtoList=clientService.getAllClients();
        return ResponseEntity.ok(clientDtoList);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto, @PathVariable Long id) {
        ClientDto clientDto1=clientService.updateClient(clientDto, id);
        return ResponseEntity.ok(clientDto1);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();

    }
}
