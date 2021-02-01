package com.devdro.sfgpetclinic.bootstrap;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.model.Pet;
import com.devdro.sfgpetclinic.model.PetType;
import com.devdro.sfgpetclinic.model.Vet;
import com.devdro.sfgpetclinic.services.OwnerService;
import com.devdro.sfgpetclinic.services.PetTypeService;
import com.devdro.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        owner1.setAddress("123 Any Street");
        owner1.setCity("Miami");
        owner1.setTelephone("11111111111");

        Pet pet1 = new Pet();
        pet1.setPetType(savedDogPetType.orElse(null));
        pet1.setName("Rosco");
        pet1.setOwner(owner1);
        pet1.setBirthday(LocalDate.now());
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Other Street");
        owner2.setCity("New York");
        owner2.setTelephone("22222222222");

        Pet pet2 = new Pet();
        pet2.setPetType(savedCatPetType.orElse(null));
        pet2.setName("Fifi");
        pet2.setOwner(owner2);
        pet2.setBirthday(LocalDate.now());
        owner2.getPets().add(pet2);

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
