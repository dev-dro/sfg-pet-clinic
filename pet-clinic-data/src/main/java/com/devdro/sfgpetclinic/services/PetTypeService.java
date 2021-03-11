package com.devdro.sfgpetclinic.services;

import com.devdro.sfgpetclinic.model.PetType;

import java.util.Optional;

public interface PetTypeService extends CrudService<PetType, Long> {
    Optional<PetType> findByName(String name);
}
