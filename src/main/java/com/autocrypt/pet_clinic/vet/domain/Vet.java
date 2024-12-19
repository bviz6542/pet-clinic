package com.autocrypt.pet_clinic.vet.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

@Getter
@Table("vet")
public class Vet {

    @Id
    @Column("vet_id")
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @MappedCollection(idColumn = "vet_id")
    private final Set<SpecialtyRef> specialtyRefs = new HashSet<>();
}
