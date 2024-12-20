package com.autocrypt.pet_clinic.owner.service;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import com.autocrypt.pet_clinic.owner.domain.Pet;
import com.autocrypt.pet_clinic.owner.dto.OwnerDto;
import com.autocrypt.pet_clinic.owner.dto.OwnerListDto;
import com.autocrypt.pet_clinic.owner.dto.PetDto;
import com.autocrypt.pet_clinic.owner.repository.OwnerRepository;
import com.autocrypt.pet_clinic.owner.repository.OwnerWithPetRaw;
import com.autocrypt.pet_clinic.pet_type.domain.PetType;
import com.autocrypt.pet_clinic.pet_type.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerListDto getOwnersByLastName(String lastName) {
        String lastNameNotNull = (lastName == null) ? "" : lastName;

        List<OwnerWithPetRaw> results = ownerRepository.findOwnersWithPetsByLastNameRaw(lastNameNotNull);

        if (results.isEmpty()) {
            return new OwnerListDto(Collections.emptyList());
        }

        List<Long> petTypeIdList = results.stream()
                .map(OwnerWithPetRaw::petTypeId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<Long, String> petTypeMap = petTypeRepository.findByIds(petTypeIdList).stream()
                .collect(Collectors.toMap(PetType::getId, PetType::getName));

        return mapToOwnerListDto(results, petTypeMap);
    }

    private OwnerListDto mapToOwnerListDto(List<OwnerWithPetRaw> results, Map<Long, String> petTypeMap) {
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

        return new OwnerListDto(new ArrayList<>(ownerMap.values()));
    }
}
