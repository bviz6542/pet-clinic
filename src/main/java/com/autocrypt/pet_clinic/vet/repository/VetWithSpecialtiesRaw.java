package com.autocrypt.pet_clinic.vet.repository;

public record VetWithSpecialtiesRaw(
        Long vetId,
        String firstName,
        String lastName,
        Long specialtyId,
        String specialtyName
) {}