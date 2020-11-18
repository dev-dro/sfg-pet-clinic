package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Vet;
import com.devdro.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Optional<Vet> save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Optional<Vet> findById(Long id) {
        return super.findById(id);
    }
}
