package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Owner;
import com.devdro.sfgpetclinic.services.CrudService;

import java.util.Optional;
import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Optional<Owner> save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return super.findById(id);
    }
}
