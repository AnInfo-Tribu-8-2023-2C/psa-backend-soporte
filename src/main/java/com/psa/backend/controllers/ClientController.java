package com.psa.backend.controllers;

import com.psa.backend.domain.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("")
    public List<ClientDTO> findAll() {
        return service.findAllMapped();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Client client) {
        Client newClient = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newClient = service.save(client);
        } catch (DataAccessException e) {
            response.put("message", "Error: Client creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", newClient);
        response.put("message", "Success: Client created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        ClientDTO newClient = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newClient = service.findByIdMapped(id);
        } catch (DataAccessException e) {
            response.put("message", "Error: Data Access Exception");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (newClient == null){
            response.put("message", "Client with ID ".concat(id.toString()).concat(" doesn't exist"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

        }
        response.put("data", newClient);
        response.put("message", "Success: Client Found");
        return new ResponseEntity<ClientDTO>(newClient, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id) {
        Client currentClient = service.findById(id);
        Client updatedClient = null;
        Map<String,Object> response = new HashMap<>();

        if (currentClient == null){
            response.put("message", "Client with ID ".concat(id.toString()).concat(" doesn't exist"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            currentClient.setName(client.getName());
            currentClient.setLastName(client.getLastName());
            currentClient.setEmail(client.getEmail());
            currentClient.setAddress(client.getAddress());
            currentClient.setPhoneNumber(client.getPhoneNumber());

            updatedClient = service.save(currentClient);

        } catch (DataAccessException e) {
            response.put("message", "Error: Client update failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client updated successfully");
        response.put("data", updatedClient);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        try{
            service.delete(id);
        } catch(DataAccessException e){
            response.put("message", "Error: Client delete failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client deleted successfully");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}