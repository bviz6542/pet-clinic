package com.autocrypt.pet_clinic.pet_type.domain;

import com.autocrypt.pet_clinic.owner.domain.Pet;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Getter
@Table("pet_type")
public class PetType {

    @Id
    @Column("pet_type_id")
    private Long id;

    private String name;

    @MappedCollection(idColumn = "pet_type_id")
    private Set<Pet> pets = new HashSet<>();
}
