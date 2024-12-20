package com.autocrypt.pet_clinic.specialty.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("specialty")
public record Specialty(
        @Id @Column("specialty_id") Long id,
        String name
) {}
