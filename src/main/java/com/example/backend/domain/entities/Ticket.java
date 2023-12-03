package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

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

    public List<Task> getListLinkedTasks() {
        return listLinkedTasks;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
