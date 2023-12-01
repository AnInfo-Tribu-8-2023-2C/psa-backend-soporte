package com.psa.backend.domain.services;

import com.psa.backend.domain.dto.MessageDTO;
import com.psa.backend.domain.entities.Ticket;
import java.util.Collection;

public interface ITicketService {

    public Collection<Ticket> getTickets();

    public Ticket createTicket(Ticket ticket);

    public Ticket getTicketById(Long id);

    public void saveTicket(Ticket ticket);

    public MessageDTO deleteTicketById(Long id);
}
