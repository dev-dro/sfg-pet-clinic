package com.devdro.sfgpetclinic.controllers;

import com.devdro.sfgpetclinic.model.Pet;
import com.devdro.sfgpetclinic.model.Visit;
import com.devdro.sfgpetclinic.services.PetService;
import com.devdro.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visit")
public class VisitController {

    public static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @ModelAttribute("")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId).orElse(null);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }

    @GetMapping("/new")
    public String initCreateProcess(@PathVariable Long petId, Model model) {
        return PETS_CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/new")
    public String processCreateProcess(@Validated Visit visit, @PathVariable Long ownerId, BindingResult result) {
        if (result.hasErrors()) {
            return PETS_CREATE_OR_UPDATE_VISIT_FORM;
        } else {
            this.visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }
}
