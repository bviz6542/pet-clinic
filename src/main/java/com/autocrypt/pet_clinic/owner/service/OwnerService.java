package com.autocrypt.pet_clinic.owner.service;

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
        Optional<OwnerWithPetRaw> ownerWithPetRawNullable = ownerRepository.findOwnerWithPetsByIdRaw(ownerId);

        if (ownerWithPetRawNullable.isEmpty()) {
            return Optional.empty();
        }

        OwnerWithPetRaw ownerWithPetRaw = ownerWithPetRawNullable.get();

        Map<Long, String> petTypeMap = findPetTypeListByOwnerWithPetRawList(List.of(ownerWithPetRaw));

        List<OwnerDto> ownerDtoList = mapToOwnerDtoList(List.of(ownerWithPetRaw), petTypeMap);

        return ownerDtoList.isEmpty() ? Optional.empty() : Optional.of(ownerDtoList.getFirst());
    }
}
