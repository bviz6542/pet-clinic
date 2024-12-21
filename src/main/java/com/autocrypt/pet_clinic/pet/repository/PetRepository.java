package com.autocrypt.pet_clinic.pet.repository;

import com.autocrypt.pet_clinic.pet.domain.Pet;
import org.springframework.data.repository.ListCrudRepository;

public interface PetRepository extends ListCrudRepository<Pet, Long> {
}
