package com.example.backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClienteDTO2 {

    private Long id;

    @JsonProperty("razon social")
    private String razonSocial;

    private String cuit;
}