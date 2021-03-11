package com.devdro.sfgpetclinic.controllers;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.model.Pet;
import com.devdro.sfgpetclinic.model.PetType;
import com.devdro.sfgpetclinic.services.OwnerService;
import com.devdro.sfgpetclinic.services.PetService;
import com.devdro.sfgpetclinic.services.PetTypeService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private static final String CREATE_OR_UPDATE_PETS_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId).orElse(null);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initCreateForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.addPet(pet);
        model.addAttribute("owner", owner);
        model.addAttribute("pet", pet);
        return CREATE_OR_UPDATE_PETS_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(Owner owner, @Validated Pet pet, BindingResult result, Model model) {
        if (StringUtils.isNotBlank(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet",  pet);
            return CREATE_OR_UPDATE_PETS_FORM;
        } else {
            this.petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId).orElse(null));
        return CREATE_OR_UPDATE_PETS_FORM;
    }

    @PostMapping("/{petId}/edit")
    public String processUpdateForm(@PathVariable Long petId, @Validated Pet pet, Owner owner, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet",  pet);
            return CREATE_OR_UPDATE_PETS_FORM;
        } else {
            owner.addPet(pet);
            this.petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
