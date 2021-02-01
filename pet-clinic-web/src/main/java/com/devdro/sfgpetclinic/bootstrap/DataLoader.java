package com.devdro.sfgpetclinic.bootstrap;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.model.PetType;
import com.devdro.sfgpetclinic.model.Vet;
import com.devdro.sfgpetclinic.services.OwnerService;
import com.devdro.sfgpetclinic.services.PetTypeService;
import com.devdro.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(PetTypeService petTypeService, OwnerService ownerService, VetService vetService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        Optional<PetType> savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        Optional<PetType> savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
