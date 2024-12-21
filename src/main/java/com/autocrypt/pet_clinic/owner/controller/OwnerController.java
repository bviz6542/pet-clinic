package com.autocrypt.pet_clinic.owner.controller;

import com.autocrypt.pet_clinic.owner.dto.OwnerDto;
import com.autocrypt.pet_clinic.owner.dto.OwnerListDto;
import com.autocrypt.pet_clinic.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping( "/json")
    public ResponseEntity<OwnerListDto> showResourcesOwnersList(@RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(ownerService.getOwnersByLastName(lastName));
    }

    @GetMapping("/find")
    public String findOwnersPage() {
        return "owners/findOwners";
    }

    @GetMapping
    public String ownerListPage(@RequestParam(required = false) String lastName, Model model) {
         OwnerListDto ownerListDto = ownerService.getOwnersByLastName(lastName);

         if (ownerListDto.ownerDtoList().isEmpty()) {
             return "owners/findOwners";
         }

         model.addAttribute("ownerList", ownerListDto);
         return "owners/ownerList";
    }

    @GetMapping("/{ownerId}")
    public String ownerDetailPage(@PathVariable Long ownerId, Model model) {
        Optional<OwnerDto> ownerDtoNullable = ownerService.getOwnerById(ownerId);

        if (ownerDtoNullable.isEmpty()) {
            return "owners/findOwners";
        }

        OwnerDto ownerDto = ownerDtoNullable.get();
        model.addAttribute("owner", ownerDto);
        return "owners/ownerDetail";
    }
}
