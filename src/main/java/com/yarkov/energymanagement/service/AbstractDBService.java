package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDBService<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    public AbstractDBService(R repository) {
        this.repository = repository;
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public T findById(ID id) throws NotFoundException {
        Optional<T> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Not found");
        }
    }

}
