package com.autocrypt.pet_clinic.vet.controller;

import com.autocrypt.pet_clinic.vet.dto.VetListDto;
import com.autocrypt.pet_clinic.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping( "/vets")
    public ResponseEntity<VetListDto> showResourcesVetList() {
        return ResponseEntity.ok(vetService.getAllVets());
    }
}
