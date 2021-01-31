package com.devdro.sfgpetclinic.services;

import java.util.Optional;
import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();
    Optional<T> findById(ID id);
    Optional<T> save(T object);
    void delete(T object);
    void deleteById(ID id);
}
