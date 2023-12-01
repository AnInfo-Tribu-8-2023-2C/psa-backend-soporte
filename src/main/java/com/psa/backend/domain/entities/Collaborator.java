package com.psa.backend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Collaborator {
    @Id
    private Integer idCollaborator;
    private String name;
    private String workPosition;
}
