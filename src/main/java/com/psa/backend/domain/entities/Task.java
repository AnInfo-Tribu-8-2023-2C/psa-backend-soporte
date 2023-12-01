package com.psa.backend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Task {
    @Id
    private Integer idTask;
    private Integer idCollaborator;
    private String name;
    private String description;
    private String state;
    private Date creationDate;
    //private int severity;
}
