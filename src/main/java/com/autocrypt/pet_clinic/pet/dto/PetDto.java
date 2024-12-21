package com.autocrypt.pet_clinic.pet.dto;

public record PetDto (
        Long id,
        String name,
        String birthDate,
        String typeName
){}
