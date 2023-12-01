package com.psa.backend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class ProductVersion {
    @Id
    private int idProductVersion;
    private int idProduct;
    private Integer CUITClient;
    private String name;
    private String description;
    private Date creationDate;
}