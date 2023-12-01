package com.psa.backend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Product {
    @Id
    private int idProduct;
    private String name;
    private String description;
    private Date creationDate;
}