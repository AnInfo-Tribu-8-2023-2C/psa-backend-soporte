package com.example.backend.domain.services;

import com.example.backend.domain.dto.MessageDTO;
import com.example.backend.domain.dto.TicketDTO;
import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.Ticket;

import java.util.List;

public interface ITicketService {

    public List<Ticket> findByProductVersionId(Long productVersionId);

   public Ticket createTicket(TicketDTO ticket);

    public Ticket getTicketById(Long id);

    public Ticket save(Ticket ticket);

    public Ticket updateTicket(Ticket ticket, Long id);

    public MessageDTO deleteTicketById(Long id);

    //public Client getClientByTicketId(Long id);

   // public void associateTask(Long ticketId, Long taskId);
}
