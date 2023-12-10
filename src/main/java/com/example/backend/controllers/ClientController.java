package com.example.backend.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.backend.domain.dto.ClienteDTO2;
import com.example.backend.domain.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("")
    public ResponseEntity<?> getClientes() throws Exception {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }

}
