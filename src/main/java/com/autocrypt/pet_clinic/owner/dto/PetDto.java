package com.autocrypt.pet_clinic.owner.dto;

public record PetDto (
        Long id,
        String name,
        String birthDate,
        String typeName
){}
