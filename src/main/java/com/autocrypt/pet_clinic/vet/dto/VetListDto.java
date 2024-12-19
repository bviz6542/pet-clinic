package com.autocrypt.pet_clinic.vet.dto;

import java.util.List;

public record VetListDto(
        List<VetDto> vetDtoList
) {}
