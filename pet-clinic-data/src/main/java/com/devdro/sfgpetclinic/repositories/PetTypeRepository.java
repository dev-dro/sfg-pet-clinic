package com.devdro.sfgpetclinic.repositories;

import com.devdro.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    Optional<PetType> findByName(String name);
}
