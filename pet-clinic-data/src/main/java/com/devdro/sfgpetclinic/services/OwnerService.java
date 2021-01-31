package com.devdro.sfgpetclinic.services;

import com.devdro.sfgpetclinic.model.Owner;

import java.util.Optional;

public interface OwnerService extends CrudService<Owner, Long> {

    Optional<Owner> findByLastName(String lastName);

}
