package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.exception.NotFoundException;

import java.util.List;

public interface AbstractDBService<T, ID> {

    void save(T entity);

    List<T> getAll();

    void delete(T entity);

    T findById(ID id) throws NotFoundException;

}
