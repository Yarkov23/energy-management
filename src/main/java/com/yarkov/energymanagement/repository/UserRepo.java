package com.yarkov.energymanagement.repository;

import com.yarkov.energymanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select distinct u from User u left join fetch u.roleList where u.email = ?1")
    User findByEmailFetchRoles(String email);

    User findByEmail(String email);

}
