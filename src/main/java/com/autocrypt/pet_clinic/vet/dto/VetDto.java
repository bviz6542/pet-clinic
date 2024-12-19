package com.autocrypt.pet_clinic.vet.dto;

import com.autocrypt.pet_clinic.vet.domain.Specialty;

import java.util.List;

public record VetDto(
        Long id,
        String firstName,
        String lastName,
        List<Specialty> specialtyList
) {}
