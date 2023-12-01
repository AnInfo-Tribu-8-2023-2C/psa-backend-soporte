package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Client;
import java.util.Collection;

public interface IClientService {

    public Collection<Client> getClients();

    public Client getClientByCUIT(Long CUIT);
}
