package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Pet;
import com.devdro.sfgpetclinic.services.CrudService;

import java.util.Optional;
import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Optional<Pet> save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return super.findById(id);
    }
}