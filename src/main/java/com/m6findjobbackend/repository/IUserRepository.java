package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount_Id(Long id);
    Boolean existsByName(String name);
}
