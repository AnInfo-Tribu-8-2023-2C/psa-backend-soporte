package com.example.backend.controllers;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.Collaborator;
import com.example.backend.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    public ResponseEntity<?> getClients() {
        WebClient webClient = WebClient.builder().baseUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes").build();
        Mono<List<Client>> clientsMono = webClient
                .get()
                .retrieve()
                .bodyToFlux(Client.class)
                .collectList();

        return ResponseEntity.ok(clientsMono.block());
    }


}
