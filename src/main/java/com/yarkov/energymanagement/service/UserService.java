package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.exception.NotFoundException;
import com.yarkov.energymanagement.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, Long, UserRepo> {

    public UserService(UserRepo repository) {
        super(repository);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
