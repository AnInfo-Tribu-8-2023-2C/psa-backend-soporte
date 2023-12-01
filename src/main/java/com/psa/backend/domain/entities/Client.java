package com.psa.backend.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Client {
    @Id
    private Integer CUITClient;
    private String name;
    private int type;
    private int razonSocial;
    private int phoneNumber;
}
