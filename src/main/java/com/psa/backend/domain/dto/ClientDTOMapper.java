package com.psa.backend.domain.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class ClientDTOMapper implements Function<Client, ClientDTO> {

    @Override
    public ClientDTO apply(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getAddress());
    }

}
