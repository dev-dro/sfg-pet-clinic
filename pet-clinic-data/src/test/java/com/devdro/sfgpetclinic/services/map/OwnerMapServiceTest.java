package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String ownerLastName = "Oliveira";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        Owner owner = Owner.builder().id(ownerId).lastName(ownerLastName).build();
        ownerMapService.delete(owner);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Optional<Owner> savedOwner = ownerMapService.save(owner);
        assertEquals(id, savedOwner.get().getId());
    }

    @Test
    void findById() {
        Optional<Owner> owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.get().getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(ownerLastName).orElse(null);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("Unreal").orElse(null);
        assertNull(owner);
    }
}
