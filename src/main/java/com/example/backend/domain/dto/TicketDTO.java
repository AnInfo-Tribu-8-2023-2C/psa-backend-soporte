package com.example.backend.domain.dto;

import com.example.backend.domain.entities.Client;
import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TicketDTO {

    private Long id;

    private ProductVersion productVersion;

    private String client;

    private String title;

    private String description;

    private String state;

    private Date createdAt;

    private Date updatedAt;

    private Integer severity;

    private Long productVersionId;

    public static TicketDTO map(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return TicketDTO.builder().id(ticket.getId()).
                title(ticket.getTitle()).
                description(ticket.getDescription()).
                state(ticket.getState()).
                severity(ticket.getSeverity()).
                createdAt(ticket.getCreatedAt()).
                updatedAt(ticket.getUpdatedAt()).
                client(ticket.getClient()).build();

    }
}
