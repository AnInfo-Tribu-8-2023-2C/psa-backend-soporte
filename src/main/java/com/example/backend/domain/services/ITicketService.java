package com.example.backend.domain.services;

import com.example.backend.domain.dto.MessageDTO;
import com.example.backend.domain.entities.Ticket;

import java.util.Collection;

public interface ITicketService {

    public Ticket save(Ticket ticket);
    public Collection<Ticket> getTickets();

    //public Ticket createTicket(Ticket ticket);

    public Ticket getTicketById(Long id);

    public Ticket updateTicket(Ticket ticket, Long id);

    public MessageDTO deleteTicketById(Long id);

   // public void associateTask(Long ticketId, Long taskId);
}
