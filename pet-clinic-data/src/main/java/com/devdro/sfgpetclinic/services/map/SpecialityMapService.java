package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Speciality;
import com.devdro.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Optional<Speciality> save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        return super.findById(id);
    }
}
