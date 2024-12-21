package com.autocrypt.pet_clinic.owner.dto;

import java.util.List;

public record OwnerDto(
        Long id,
        String firstName,
        String lastName,
        String address,
        String city,
        String telephone,
        List<PetDto> petDtoList
) {

    public static OwnerDto createEmpty() {
        return new OwnerDto(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
