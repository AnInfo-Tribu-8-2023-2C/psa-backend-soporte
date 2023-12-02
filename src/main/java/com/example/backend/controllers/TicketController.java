package com.example.backend.controllers;

import com.example.backend.domain.entities.Ticket;
import com.example.backend.domain.services.ITicketService;
import com.example.backend.domain.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping
    public ResponseEntity<?> getTickets() {
        return new ResponseEntity<>(ticketService.getTickets(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.getTicketById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(ticketService.updateTicket(ticket, id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PutMapping("/{idTicket}/link-task/{idTask}")
    public ResponseEntity<?> linkTask(@PathVariable Long idTicket, @PathVariable Long idTask) {
        return new ResponseEntity<>(ticketService.linkTask(idTicket, idTask), HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> deleteTicketById(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.deleteTicketById(id), HttpStatus.OK);
    }
}