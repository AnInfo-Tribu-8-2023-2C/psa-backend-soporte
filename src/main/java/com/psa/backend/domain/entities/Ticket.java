package com.psa.backend.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long versionId;

    private Long CUITClient;

    private String title;

    private String description;

    private String state;

    private Date creationDate;

    private int severity;

    @ManyToMany
    @JoinTable(
            name = "ticket_task",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> listLinkedTasks = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }
}
