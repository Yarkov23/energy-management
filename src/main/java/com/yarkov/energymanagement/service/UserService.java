package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.exception.NotFoundException;
import com.yarkov.energymanagement.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractDBService<User, Long, UserRepo> {

    public UserService(UserRepo repository) {
        super(repository);
    }

    @Override
    public void save(User entity) {
        super.save(entity);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public User findById(Long aLong) throws NotFoundException {
        return super.findById(aLong);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
