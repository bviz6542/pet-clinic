package com.autocrypt.pet_clinic.vet.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("specialty")
public record Specialty(
        @Id Long id,
        String name
) {}
