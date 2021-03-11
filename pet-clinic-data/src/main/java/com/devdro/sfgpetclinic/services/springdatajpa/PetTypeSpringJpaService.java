package com.devdro.sfgpetclinic.services.springdatajpa;

import com.devdro.sfgpetclinic.model.PetType;
import com.devdro.sfgpetclinic.repositories.PetTypeRepository;
import com.devdro.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSpringJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSpringJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public Optional<PetType> findById(Long id) {
        return petTypeRepository.findById(id);
    }

    @Override
    public Optional<PetType> save(PetType object) {
        return Optional.of(petTypeRepository.save(object));
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }

    @Override
    public Optional<PetType> findByName(String name) {
        return petTypeRepository.findByName(name);
    }
}
