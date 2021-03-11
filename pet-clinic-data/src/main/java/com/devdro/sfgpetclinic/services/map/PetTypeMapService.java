package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.PetType;
import com.devdro.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public Optional<PetType> save(PetType object) {
        return super.save(object);
    }

    @Override
    public Optional<PetType> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Optional<PetType> findByName(String name) {
        return Optional.empty();
    }
}
