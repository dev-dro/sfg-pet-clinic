package com.devdro.sfgpetclinic.controllers;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.model.Pet;
import com.devdro.sfgpetclinic.model.Visit;
import com.devdro.sfgpetclinic.services.PetService;
import com.devdro.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    Owner owner;
    Pet pet;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).build();

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initCreateProcess() throws Exception {
        when(petService.findById(any())).thenReturn(Optional.of(Pet.builder().id(1L).build()));

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processCreateProcess() throws Exception {
        when(petService.findById(any())).thenReturn(Optional.of(Pet.builder().id(1L).build()));
        when(visitService.save(any())).thenReturn(Optional.of(Visit.builder().id(1L).build()));

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(visitService).save(any());
    }

    @Test
    void initUpdateProcess() throws Exception {
        when(petService.findById(any())).thenReturn(Optional.of(Pet.builder().id(1L).build()));

        mockMvc.perform(get("/owners/1/pets/1/visits/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processUpdateProcess() throws Exception {
        when(petService.findById(any())).thenReturn(Optional.of(Pet.builder().id(1L).build()));
        when(visitService.save(any())).thenReturn(Optional.of(Visit.builder().id(1L).build()));

        mockMvc.perform(post("/owners/1/pets/1/visits/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(visitService).save(any());
    }
}
