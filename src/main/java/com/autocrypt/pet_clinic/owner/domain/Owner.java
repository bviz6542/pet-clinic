package com.autocrypt.pet_clinic.owner.domain;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Getter
@Table("owner")
public class Owner {

    @Id
    @Column("owner_id")
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String address;

    @NotEmpty
    private String city;

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @MappedCollection(idColumn = "owner_id")
    private final Set<Pet> pets = new HashSet<>();
}
