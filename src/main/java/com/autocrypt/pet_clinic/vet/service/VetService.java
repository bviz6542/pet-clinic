package com.autocrypt.pet_clinic.vet.service;

import com.autocrypt.pet_clinic.specialty.domain.Specialty;
import com.autocrypt.pet_clinic.vet.dto.VetDto;
import com.autocrypt.pet_clinic.vet.dto.VetListDto;
import com.autocrypt.pet_clinic.vet.repository.VetRepository;
import com.autocrypt.pet_clinic.vet.repository.VetWithSpecialtiesRaw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    @Transactional(readOnly = true)
    public VetListDto getAllVets() {
        List<VetWithSpecialtiesRaw> vetWithSpecialtiesRawList = vetRepository.findAllWithSpecialtiesRaw();

        Map<Long, List<VetWithSpecialtiesRaw>> groupedByVetId = vetWithSpecialtiesRawList.stream()
                .collect(Collectors.groupingBy(VetWithSpecialtiesRaw::vetId));

        List<VetDto> vetDtoList = groupedByVetId.values().stream()
                .map(this::mapToVetDto)
                .collect(Collectors.toList());

        return new VetListDto(vetDtoList);
    }

    private VetDto mapToVetDto(List<VetWithSpecialtiesRaw> groupedByVetId) {
        VetWithSpecialtiesRaw firstRow = groupedByVetId.getFirst();

        List<Specialty> specialties = groupedByVetId.stream()
                .filter(row -> row.specialtyId() != null)
                .map(row -> new Specialty(row.specialtyId(), row.specialtyName()))
                .collect(Collectors.toList());

        return new VetDto(firstRow.vetId(), firstRow.firstName(), firstRow.lastName(), specialties);
    }
}
