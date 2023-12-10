package com.example.backend.domain.services;

import com.example.backend.domain.dto.ClienteDTO2;
import com.example.backend.domain.entities.Client;

import java.util.Collection;
import java.util.List;

public interface IClientService {

    public List<ClienteDTO2> getClients() throws Exception;

    public Client getClientByCUIT(Long CUIT);
}
