package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.exception.NotFoundException;
import com.yarkov.energymanagement.repository.CompanyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends AbstractDBService<Company, Long, CompanyRepo> {

    public CompanyService(CompanyRepo repository) {
        super(repository);
    }

    @Override
    public void save(Company entity) {
        super.save(entity);
    }

    @Override
    public List<Company> getAll() {
        return super.getAll();
    }

    @Override
    public void delete(Company entity) {
        super.delete(entity);
    }

    @Override
    public Company findById(Long aLong) throws NotFoundException {
        return super.findById(aLong);
    }

    public Company findByName(String name) {
        return repository.findCompanyByName(name);
    }
}
