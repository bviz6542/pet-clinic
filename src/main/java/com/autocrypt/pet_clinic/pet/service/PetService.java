package com.autocrypt.pet_clinic.pet.service;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import com.autocrypt.pet_clinic.owner.repository.OwnerRepository;
import com.autocrypt.pet_clinic.pet.domain.Pet;
import com.autocrypt.pet_clinic.pet.dto.InitialPetAddFormDto;
import com.autocrypt.pet_clinic.pet.repository.PetRepository;
import com.autocrypt.pet_clinic.pet_type.domain.PetType;
import com.autocrypt.pet_clinic.pet_type.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public InitialPetAddFormDto getInitialPetAddFormDto(Long ownerId) {
        Owner owner = ownerRepository.findOwnerByIdWithNoRelationship(ownerId)
                .orElseThrow(IllegalArgumentException::new);

        List<PetType> petTypeList = petTypeRepository.findAll();

        return new InitialPetAddFormDto(owner.getId(), owner.getFirstName(), owner.getLastName(), null, null, null, petTypeList);
    }

    @Transactional
    public void addPet(InitialPetAddFormDto initialPetAddFormDto) {
        Owner owner = ownerRepository.findById(initialPetAddFormDto.ownerId())
                .orElseThrow(IllegalArgumentException::new);

        PetType petType = petTypeRepository.findByName(initialPetAddFormDto.petTypeName())
                .orElseThrow(IllegalArgumentException::new);

        Pet pet = Pet.builder()
                .name(initialPetAddFormDto.petName())
                .birthDate(initialPetAddFormDto.birthDate())
                .petTypeId(petType.getId())
                .build();

        owner.addPet(pet);
        ownerRepository.save(owner);
    }
}
