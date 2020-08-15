package com.rodrigorp.harrypotterapi.service;

import java.util.List;

public interface GenericService<E, M> {

    E save(E entity);

    void deleteById(M id);

    E findById(M id);

    List<E> findAll();

    E update(E entity, M id);
}