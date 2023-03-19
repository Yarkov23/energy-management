package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.exception.NotFoundException;
import com.yarkov.energymanagement.repository.CompanyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends BaseService<Company, Long, CompanyRepo> {

    public CompanyService(CompanyRepo repository) {
        super(repository);
    }

    public Company findByName(String name) {
        return repository.findCompanyByName(name);
    }
}
