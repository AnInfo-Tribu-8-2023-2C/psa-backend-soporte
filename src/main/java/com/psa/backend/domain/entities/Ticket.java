package com.psa.backend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    private Integer idTicket;
    private String title;
    private String description;
    private String state;
    private Date creationDate;
    private int severity;


}