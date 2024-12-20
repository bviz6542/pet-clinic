package com.autocrypt.pet_clinic.vet.controller;

import com.autocrypt.pet_clinic.vet.dto.VetListDto;
import com.autocrypt.pet_clinic.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    @GetMapping( "/json")
    public ResponseEntity<VetListDto> showResourcesVetList() {
        return ResponseEntity.ok(vetService.getAllVets());
    }

    @GetMapping
    public String vetListPage(Model model) {
        model.addAttribute("vetList", vetService.getAllVets());
        return "vets/vetList";
    }
}
