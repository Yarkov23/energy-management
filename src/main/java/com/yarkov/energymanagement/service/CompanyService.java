package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.repository.CompanyRepo;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CompanyService extends BaseService<Company, Long, CompanyRepo> {

    private final UserService userService;

    public CompanyService(CompanyRepo repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    public Company findByName(String name) {
        return repository.findCompanyByName(name);
    }

    public Company getCompanyByUser(Principal principal) {
        User currentUser = userService.findByEmail(principal.getName());
        return repository.findCompanyByName(currentUser.getCompany().getName());
    }

}
