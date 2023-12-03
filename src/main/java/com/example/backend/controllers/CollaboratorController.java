package com.example.backend.controllers;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.Collaborator;
import com.example.backend.domain.services.ICollaboratorService;
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
@RequestMapping("/collaborators")
public class CollaboratorController {
    @Autowired
    private ICollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<?> getCollaborators() {
        WebClient webClient = WebClient.builder().baseUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos").build();
        Mono<List<Collaborator>> collaboratorsMono = webClient
                .get()
                .retrieve()
                .bodyToFlux(Collaborator.class)
                .collectList();

        return ResponseEntity.ok(collaboratorsMono.block());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollaboratorById(@PathVariable Long id) {
        WebClient webClient = WebClient.builder().baseUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos").build();
        Mono<Collaborator> collaboratorMono = webClient.get()
                .uri("", id)
                .retrieve()
                .bodyToMono(Collaborator.class);

        return collaboratorMono.map(collaborator -> new ResponseEntity<>(collaborator, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)).block();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCollaboratorByName(@PathVariable String name) {
        WebClient webClient = WebClient.builder().baseUrl("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos").build();
        Mono<Collaborator> collaboratorMono = webClient.get()
                .uri("", name)
                .retrieve()
                .bodyToMono(Collaborator.class);

        return collaboratorMono.map(collaborator -> new ResponseEntity<>(collaborator, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)).block();
    }
}
