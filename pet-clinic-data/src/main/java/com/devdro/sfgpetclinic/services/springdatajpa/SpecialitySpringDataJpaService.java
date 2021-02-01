package com.devdro.sfgpetclinic.services.springdatajpa;

import com.devdro.sfgpetclinic.model.Speciality;
import com.devdro.sfgpetclinic.repositories.SpecialityRepository;
import com.devdro.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySpringDataJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySpringDataJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        return specialityRepository.findById(id);
    }

    @Override
    public Optional<Speciality> save(Speciality object) {
        return Optional.of(specialityRepository.save(object));
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
