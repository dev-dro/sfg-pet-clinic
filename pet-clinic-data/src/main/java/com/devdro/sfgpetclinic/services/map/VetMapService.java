package com.devdro.sfgpetclinic.services.map;

import com.devdro.sfgpetclinic.model.Speciality;
import com.devdro.sfgpetclinic.model.Vet;
import com.devdro.sfgpetclinic.services.SpecialityService;
import com.devdro.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

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
        if (object != null && !object.getSpecialities().isEmpty()) {
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    Optional<Speciality> savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.orElseThrow(RuntimeException::new).getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public Optional<Vet> findById(Long id) {
        return super.findById(id);
    }
}
