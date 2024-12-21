package com.autocrypt.pet_clinic.pet.controller;

import com.autocrypt.pet_clinic.owner.dto.OwnerDto;
import com.autocrypt.pet_clinic.pet.service.PetService;
import com.autocrypt.pet_clinic.pet.dto.InitialPetAddFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private final PetService petService;

    @GetMapping("/new")
    public String initialAddPetPage(@PathVariable Long ownerId, Model model) {
        InitialPetAddFormDto initialPetAddFormDto = petService.getInitialPetAddFormDto(ownerId);
        model.addAttribute("initialPetAddForm", initialPetAddFormDto);
        return "pets/addPet";
    }

    @PostMapping("/new")
    public String processAddPetPage(@ModelAttribute("initialPetAddForm") InitialPetAddFormDto initialPetAddFormDto) {
        petService.addPet(initialPetAddFormDto);
        return "redirect:/owners/" + initialPetAddFormDto.ownerId();
    }
}

