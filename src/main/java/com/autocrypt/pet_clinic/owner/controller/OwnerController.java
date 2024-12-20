package com.autocrypt.pet_clinic.owner.controller;

import com.autocrypt.pet_clinic.owner.domain.Owner;
import com.autocrypt.pet_clinic.owner.dto.OwnerListDto;
import com.autocrypt.pet_clinic.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping( "/api/owners")
    public ResponseEntity<OwnerListDto> showResourcesOwnersList(@RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(ownerService.getOwnersByLastName(lastName));
    }
}
