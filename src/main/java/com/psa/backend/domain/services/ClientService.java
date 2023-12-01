package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Client;
import com.psa.backend.domain.repositories.ClientRepository;
import com.psa.backend.domain.handlers.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Collection<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientByCUIT(Long CUIT) {
        Optional<Client> clientOptional =  clientRepository.findByCUIT(CUIT);
        if (clientOptional.isEmpty()) {
            throw new NotFoundException(String.format("Client with id: %d not found", CUIT));
        }
        return clientOptional.get();
    }
}
