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

}
