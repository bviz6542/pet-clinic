package com.autocrypt.pet_clinic.vet.domain;

import org.springframework.data.relational.core.mapping.Table;

@Table("vet_specialty")
public record SpecialtyRef(
        Long specialty
) {}
