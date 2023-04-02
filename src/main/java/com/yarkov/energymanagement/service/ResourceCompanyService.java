package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Resource;
import com.yarkov.energymanagement.entity.ResourceCompany;
import com.yarkov.energymanagement.entity.id.ResourceCompanyId;
import com.yarkov.energymanagement.repository.ResourceCompanyRepo;
import org.springframework.stereotype.Service;

@Service
public class ResourceCompanyService extends BaseService<ResourceCompany, ResourceCompanyId, ResourceCompanyRepo> {
    public ResourceCompanyService(ResourceCompanyRepo repository) {
        super(repository);
    }

    ResourceCompany findByCompanyIdAndResourceId(Company company, Resource resource) {
        return repository.findResourceCompanyByCompanyAndResource(company, resource);
    }

    ResourceCompany findFirstByCompany(Company company) {
        return repository.findFirstByCompany(company);
    }

}
