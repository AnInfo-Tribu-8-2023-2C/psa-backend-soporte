package com.psa.backend.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long idCollaborator;

    private String name;

    private String description;

    private String state;

    private Date creationDate;

    //private int severity;

    @ManyToMany(mappedBy = "listLinkedTasks")
    private List<Ticket> listLinkedTickets = new ArrayList<>();

    public List<Ticket> getListLinkedTickets() {
        return listLinkedTickets;
    }
}
