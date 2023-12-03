package com.example.backend.controllers;

import com.example.backend.domain.services.ICollaboratorService;
import com.example.backend.domain.entities.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController {
    @Autowired
    private ICollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<?> getCollaborators() {
        RestTemplate restTemplate = new RestTemplate();
        String apiClientsUrl = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";

        return restTemplate.getForEntity(apiClientsUrl, Collaborator[].class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollaboratorById(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String apiClientsUrl = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
        Map<String, Long> params = new HashMap<>();
        params.put("legajo", id);
        Collaborator collaborator = restTemplate.getForEntity(apiClientsUrl,Collaborator.class, params).getBody();
        return new ResponseEntity<>(collaborator, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCollaboratorByName(@PathVariable String name) {
        RestTemplate restTemplate = new RestTemplate();
        String apiClientsUrl = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
        Map<String, String> params = new HashMap<>();
        params.put("Nombre", name);
        Collaborator collaborator = restTemplate.getForEntity(apiClientsUrl,Collaborator.class, params).getBody();
        return new ResponseEntity<>(collaborator, HttpStatus.OK);
    }
}
