package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Ticket;
import com.psa.backend.domain.repositories.TicketRepository;
import com.psa.backend.domain.handlers.NotFoundException;
import com.psa.backend.domain.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        Optional<Ticket> ticketOptional =  ticketRepository.findById(id);
        if (ticketOptional.isEmpty()) {
            throw new NotFoundException(String.format("Ticket with id: %d not found", id));
        }
        return ticketOptional.get();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public MessageDTO deleteTicketById(Long id) {
        try {
            ticketRepository.deleteById(id);
            return new MessageDTO(/*String.format("Delete ticket with id: %d success", id)*/);
        } catch (Exception e) {
            throw new NotFoundException("Ticket not found");
        }
    }
}
