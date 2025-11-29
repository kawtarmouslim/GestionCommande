package org.example.getioncommande.service;

import lombok.AllArgsConstructor;
import org.example.getioncommande.dto.ClientDto;
import org.example.getioncommande.entites.Client;
import org.example.getioncommande.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;


    public ClientDto createClient(ClientDto clientDto) {

    Client client = modelMapper.map(clientDto, Client.class);
    Client savedClient = clientRepository.save(client);
    return modelMapper.map(savedClient, ClientDto.class);

    }
    public ClientDto updateClient(ClientDto clientDto, Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        client.setNom(clientDto
                .getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(clientDto.getAdresse());
        client.setTel(clientDto.getTel());
        Client savedClient = clientRepository.save(client);

        return modelMapper.map(savedClient, ClientDto.class);
    }


    public ClientDto getClientById(Long id) {

        return modelMapper.map(clientRepository.findClientsByIdClient(id), ClientDto.class);

    }


    public List<ClientDto> getAllClients() {

       List<Client>clients=clientRepository.findAll();
       return clients
               .stream()
               .map(client -> modelMapper.map(client, ClientDto.class))
               .collect(Collectors.toList());
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

}
