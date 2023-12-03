package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    @CreationTimestamp
    @Column(name = "creationDate")
    private Date createdAt;

    private int severity;

    @ManyToMany
    @JoinTable(
            name = "ticket_task",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> listLinkedTasks = new ArrayList<>();

}
