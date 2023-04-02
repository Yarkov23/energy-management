package com.yarkov.energymanagement.repository;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Resource;
import com.yarkov.energymanagement.entity.ResourceCompany;
import com.yarkov.energymanagement.entity.id.ResourceCompanyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceCompanyRepo extends JpaRepository<ResourceCompany, ResourceCompanyId> {

    ResourceCompany findResourceCompanyByCompanyAndResource(Company company, Resource resource);

    ResourceCompany findFirstByCompany(Company company);

}
