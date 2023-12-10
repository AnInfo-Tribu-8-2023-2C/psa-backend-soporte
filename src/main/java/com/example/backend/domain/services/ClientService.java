package com.example.backend.domain.services;

import com.example.backend.domain.dto.ClienteDTO2;
import com.example.backend.domain.entities.Client;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.ClientRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ClientService implements IClientService {
    @Autowired
    ClientRepository clientRepository;

    private String clientsEndpoint = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes";

    @Override
    public List<ClienteDTO2> getClients() throws Exception {
        try {
            URL url = new URL(clientsEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }
                ObjectMapper objectMapper = new ObjectMapper();
                List<ClienteDTO2> clientes = objectMapper.readValue(String.valueOf(sb), new TypeReference<List<ClienteDTO2>>() {});
                return clientes;
            }
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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
