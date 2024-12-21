package com.autocrypt.pet_clinic.pet.dto;

import com.autocrypt.pet_clinic.pet_type.domain.PetType;

import java.time.LocalDate;
import java.util.List;

public record InitialPetAddFormDto(
        Long ownerId,
        String firstName,
        String lastName,
        String petName,
        LocalDate birthDate,
        String petTypeName,
        List<PetType> petTypeList
) {}
