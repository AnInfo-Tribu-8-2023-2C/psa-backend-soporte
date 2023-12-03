package com.example.backend.domain.services;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> clientOptional =  clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new NotFoundException(String.format("Client with id: %d not found", id));
        }
        return clientOptional.get();
    }
}
