package com.autocrypt.pet_clinic.owner.service;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import com.autocrypt.pet_clinic.pet.domain.Pet;
import com.autocrypt.pet_clinic.owner.dto.OwnerDto;
import com.autocrypt.pet_clinic.owner.dto.OwnerListDto;
import com.autocrypt.pet_clinic.pet.dto.PetDto;
import com.autocrypt.pet_clinic.owner.repository.OwnerRepository;
import com.autocrypt.pet_clinic.owner.repository.OwnerWithPetRaw;
import com.autocrypt.pet_clinic.pet_type.domain.PetType;
import com.autocrypt.pet_clinic.pet_type.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;

    @Transactional(readOnly = true)
    public OwnerListDto getOwnersByLastName(String lastName) {
        String lastNameNotNull = (lastName == null) ? "" : lastName;

        List<OwnerWithPetRaw> results = ownerRepository.findOwnersWithPetsByLastNameRaw(lastNameNotNull);

        if (results.isEmpty()) {
            return new OwnerListDto(Collections.emptyList());
        }

        Map<Long, String> petTypeMap = getPetTypeMap(results.stream()
                .map(OwnerWithPetRaw::petTypeId)
                .filter(Objects::nonNull)
                .distinct()
                .toList());

        return new OwnerListDto(mapToOwnerDtoList(results, petTypeMap));
    }

    @Transactional(readOnly = true)
    public OwnerDto getOwnerById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(IllegalArgumentException::new);

        Map<Long, String> petTypeMap = getPetTypeMap(owner.getPets().stream()
                .map(Pet::getPetTypeId)
                .distinct()
                .toList());

        List<PetDto> petDtoList = mapToPetDtoList(owner.getPets(), petTypeMap);

        return new OwnerDto(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getLastName(), owner.getAddress(), owner.getCity(), petDtoList);
    }

    private Map<Long, String> getPetTypeMap(List<Long> petTypeIdList) {
        if (petTypeIdList.isEmpty()) {
            return Collections.emptyMap();
        }

        return petTypeRepository.findByIds(petTypeIdList).stream()
                .collect(Collectors.toMap(PetType::getId, PetType::getName));
    }

    private List<OwnerDto> mapToOwnerDtoList(List<OwnerWithPetRaw> results, Map<Long, String> petTypeMap) {
        Map<Long, OwnerDto> ownerMap = new LinkedHashMap<>();

        for (OwnerWithPetRaw row : results) {
            Long ownerId = row.ownerId();

            OwnerDto ownerDto = ownerMap.computeIfAbsent(ownerId, id -> new OwnerDto(
                    ownerId,
                    row.firstName(),
                    row.lastName(),
                    row.address(),
                    row.city(),
                    row.telephone(),
                    new ArrayList<>()
            ));

            if (row.petId() != null) {
                PetDto petDto = mapToPetDto(row, petTypeMap.get(row.petTypeId()));
                ownerDto.petDtoList().add(petDto);
            }
        }

        return new ArrayList<>(ownerMap.values());
    }

    private PetDto mapToPetDto(OwnerWithPetRaw row, String petTypeName) {
        return new PetDto(
                row.petId(),
                row.petName(),
                row.petBirthDate() != null ? row.petBirthDate().toString() : null,
                petTypeName
        );
    }

    private List<PetDto> mapToPetDtoList(Set<Pet> pets, Map<Long, String> petTypeMap) {
        return pets.stream()
                .map(pet -> new PetDto(
                        pet.getId(),
                        pet.getName(),
                        pet.getBirthDate().toString(),
                        petTypeMap.get(pet.getPetTypeId())))
                .toList();
    }

    @Transactional
    public Long addOwner(OwnerDto ownerDto) {
        Owner owner = Owner.builder()
                .firstName(ownerDto.firstName())
                .lastName(ownerDto.lastName())
                .address(ownerDto.address())
                .city(ownerDto.city())
                .telephone(ownerDto.telephone())
                .build();

        Owner savedOwner = ownerRepository.save(owner);

        return savedOwner.getId();
    }

    @Transactional
    public Long editOwner(Long ownerId, OwnerDto ownerDto) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(IllegalArgumentException::new);

        owner.edit(ownerDto.firstName(), ownerDto.lastName(), ownerDto.address(), ownerDto.city(), ownerDto.telephone());

        Owner editedOwner = ownerRepository.save(owner);

        return editedOwner.getId();
    }
}
