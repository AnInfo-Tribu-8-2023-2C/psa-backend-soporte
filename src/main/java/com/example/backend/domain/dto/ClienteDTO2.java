package com.example.backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClienteDTO2 {

    private Long id;

    @JsonProperty("razon social")
    private String razonSocial;

    @JsonProperty("CUIT")
    private String cuit;
}