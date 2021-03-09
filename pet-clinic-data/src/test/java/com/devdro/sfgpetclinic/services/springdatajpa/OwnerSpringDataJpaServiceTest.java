package com.devdro.sfgpetclinic.services.springdatajpa;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.repositories.OwnerRepository;
import com.devdro.sfgpetclinic.repositories.PetRepository;
import com.devdro.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSpringDataJpaService service;

    Owner returnedOwner;

    public static final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        HashSet<Owner> returnedOwners = new HashSet<>();
        returnedOwners.add(Owner.builder().id(1L).build());
        returnedOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));

        Owner owner = service.findById(1L).orElse(null);

        assertNotNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner ownerToSave = Owner.builder().id(1L).build();
        Owner savedOwner = service.save(ownerToSave).orElse(null);

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(returnedOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        Owner returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();

        when(ownerRepository.findByLastName(any())).thenReturn(Optional.of(returnedOwner));

        Owner smith = service.findByLastName(LAST_NAME).orElse(null);

        assertNotNull(smith);
        assertEquals(LAST_NAME, smith.getLastName());
    }
}
