package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.Role;
import com.m6findjobbackend.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}