package com.mytask.repository;

import java.util.ArrayList;
import java.util.Optional;

public interface CrudRepository<E, T> {
    E save(E user);

    ArrayList<E> findAll();

    Optional<E> findById(T id);

    void update(E user);

    Optional<E> deleteById(T id);


}
