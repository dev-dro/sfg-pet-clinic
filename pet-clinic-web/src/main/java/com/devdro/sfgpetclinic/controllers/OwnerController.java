package com.devdro.sfgpetclinic.controllers;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping({"/owners"})
public class OwnerController {

    public static final String CREATE_OR_UPDATE_OWNERS_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @RequestMapping("")
    public String processFindOwners(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> owners = this.ownerService.findAllByLastNameLike(owner.getLastName());
        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            owner = owners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("owners", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject("owner", this.ownerService.findById(ownerId).orElse(null));
        return modelAndView;
    }

    @GetMapping("/new")
    public String initCreateForm(Model model) {
        model.addAttribute("owner", new Owner());
        return CREATE_OR_UPDATE_OWNERS_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(@Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNERS_FORM;
        } else {
            Owner savedOwner = this.ownerService.save(owner).orElse(null);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", this.ownerService.findById(ownerId).orElse(null));
        return CREATE_OR_UPDATE_OWNERS_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@PathVariable Long ownerId, @Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNERS_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = this.ownerService.save(owner).orElse(null);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
