package com.yarkov.energymanagement.repository;

import com.yarkov.energymanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {

    Resource findByResourceName(String name);

}
