package com.example.backend.controllers;

import com.example.backend.domain.services.ICollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController {
    @Autowired
    private ICollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<?> getCollaborators() {
        return new ResponseEntity<>(collaboratorService.getCollaborators(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollaboratorById(@PathVariable Long id) {
        return new ResponseEntity<>(collaboratorService.getCollaboratorById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCollaboratorByName(@PathVariable String name) {
        return new ResponseEntity<>(collaboratorService.getCollaboratorByName(name), HttpStatus.OK);
    }
}
