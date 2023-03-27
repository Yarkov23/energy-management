package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Resource;
import com.yarkov.energymanagement.repository.ResourceRepo;
import org.springframework.stereotype.Service;

@Service
public class ResourceService extends BaseService<Resource, Long, ResourceRepo> {

    public ResourceService(ResourceRepo repository) {
        super(repository);
    }

    public Resource findByResourceName(String query) {
        return repository.findByResourceName(query);
    }

}
