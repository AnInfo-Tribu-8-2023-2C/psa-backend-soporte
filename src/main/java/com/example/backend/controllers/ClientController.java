package com.example.backend.controllers;

import com.example.backend.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    public ResponseEntity<?> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<?> getClientByCUIT(@PathVariable Long cuit) {
        return new ResponseEntity<>(clientService.getClientByCUIT(cuit), HttpStatus.OK);
    }
}
