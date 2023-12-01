package com.psa.backend.domain.dto;

public record ClientDTO (
    Long id,
    String name,
    String email,
    Integer phoneNumber,
    String address

    ){

}
