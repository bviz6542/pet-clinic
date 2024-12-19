package com.autocrypt.pet_clinic.owner.repository;

import java.time.LocalDate;

public record OwnerWithPetRaw(
        Long ownerId,
        String firstName,
        String lastName,
        String address,
        String city,
        String telephone,
        Long petId,
        String petName,
        LocalDate petBirthDate,
        Long petTypeId
) {}
