package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>>
        implements AbstractDBService<T, ID> {

    protected final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public T findById(ID id) throws NotFoundException {
        Optional<T> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
