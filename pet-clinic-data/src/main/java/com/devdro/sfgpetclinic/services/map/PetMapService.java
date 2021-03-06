package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Pet;
import com.devdro.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
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
        return super.save(object);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return super.findById(id);
    }
}
