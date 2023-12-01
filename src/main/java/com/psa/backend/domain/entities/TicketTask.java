package com.psa.backend.domain.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@Entity
public class TicketTask {
    @Id
    @Column(name = "ticket_id")
    private Long ticketId;

    @Id
    @Column(name = "task_id")
    private Long taskId;

    public TicketTask() {
    }

    public TicketTask(Long ticketId, Long taskId) {
        this.ticketId = ticketId;
        this.taskId = taskId;
    }
}