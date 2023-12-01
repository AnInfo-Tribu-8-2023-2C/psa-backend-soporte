package com.psa.backend.domain.services;

import com.psa.backend.domain.dto.MessageDTO;
import com.psa.backend.domain.entities.TicketTask;
import java.util.Collection;

public interface ITicketTaskService {
    public void createTicketTask(Long ticketId, Long taskId);

    public Collection<TicketTask> getByTicketId(Long ticketId);

    public Collection<TicketTask> getByTaskId(Long taskId);

    public MessageDTO deleteByTicketId(Long ticketId);

    public MessageDTO deleteByTaskId(Long taskId);
}
