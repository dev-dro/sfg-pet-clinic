package com.devdro.sfgpetclinic.services.springdatajpa;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.repositories.OwnerRepository;
import com.devdro.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerSpringDataJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Optional<Owner> save(Owner object) {
        return Optional.of(ownerRepository.save(object));
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Optional<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
