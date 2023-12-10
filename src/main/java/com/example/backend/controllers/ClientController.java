package com.example.backend.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.backend.domain.dto.ClienteDTO2;
import com.example.backend.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {
    @Autowired
    private IClientService clientService;
    private String clientsEndpoint = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes";

    @GetMapping("")
    public List<ClienteDTO2> getClientes() throws Exception {
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
            throw new Exception("Error getting recursos information");
        }
    }

}
