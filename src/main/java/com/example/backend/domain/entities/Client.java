package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "CUIT")
    private String CUIT;

    private String name;

    private int type;

    @Column(name = "razon social")
    private String razonSocial;

    private int phoneNumber;
}
