package com.autocrypt.pet_clinic.owner.service;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import com.autocrypt.pet_clinic.owner.dto.OwnerDto;
import com.autocrypt.pet_clinic.owner.dto.OwnerListDto;
import com.autocrypt.pet_clinic.owner.dto.PetDto;
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

        Map<Long, String> petTypeMap = findPetTypeListByOwnerWithPetRawList(results);

        return new OwnerListDto(mapToOwnerDtoList(results, petTypeMap));
    }

    private Map<Long, String> findPetTypeListByOwnerWithPetRawList(List<OwnerWithPetRaw> ownerWithPetRawList) {
        List<Long> petTypeIdList = ownerWithPetRawList.stream()
                .map(OwnerWithPetRaw::petTypeId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

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
                String petTypeName = petTypeMap.getOrDefault(row.petTypeId(), null);

                PetDto petDto = new PetDto(
                        row.petId(),
                        row.petName(),
                        row.petBirthDate() != null ? row.petBirthDate().toString() : null,
                        petTypeName
                );

                ownerDto.petDtoList().add(petDto);
            }
        }

        return new ArrayList<>(ownerMap.values());
    }

    @Transactional(readOnly = true)
    public Optional<OwnerDto> getOwnerById(Long ownerId) {
        List<OwnerWithPetRaw> ownerWithPetRawList = ownerRepository.findOwnerWithPetsByIdRaw(ownerId);

        if (ownerWithPetRawList.isEmpty()) {
            return Optional.empty();
        }

        Map<Long, String> petTypeMap = findPetTypeListByOwnerWithPetRawList(ownerWithPetRawList);

        List<OwnerDto> ownerDtoList = mapToOwnerDtoList(ownerWithPetRawList, petTypeMap);

        return ownerDtoList.isEmpty() ? Optional.empty() : Optional.of(ownerDtoList.getFirst());
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
        Owner owner = Owner.builder()
                .id(ownerId)
                .firstName(ownerDto.firstName())
                .lastName(ownerDto.lastName())
                .address(ownerDto.address())
                .city(ownerDto.city())
                .telephone(ownerDto.telephone())
                .build();

        Owner editedOwner = ownerRepository.save(owner);

        return editedOwner.getId();
    }
}
