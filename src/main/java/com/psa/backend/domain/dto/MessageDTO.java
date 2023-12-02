package com.psa.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Builder
public class MessageDTO {
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }
}
