package com.autocrypt.pet_clinic.owner.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Table("pet")
public class Pet {

    @Id
    @Column("pet_id")
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column("pet_type_id")
    private Long petTypeId;
}
