package com.autocrypt.pet_clinic.pet_type.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("pet_type")
public class PetType {

    @Id
    @Column("pet_type_id")
    private Long id;

    private String name;
}
