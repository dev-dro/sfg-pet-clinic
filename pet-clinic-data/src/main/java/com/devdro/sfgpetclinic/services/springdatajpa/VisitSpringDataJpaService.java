package com.devdro.sfgpetclinic.services.springdatajpa;

import com.devdro.sfgpetclinic.model.Visit;
import com.devdro.sfgpetclinic.repositories.VisitRepository;
import com.devdro.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSpringDataJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSpringDataJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public Optional<Visit> save(Visit visit) {
        return Optional.of(visitRepository.save(visit));
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
