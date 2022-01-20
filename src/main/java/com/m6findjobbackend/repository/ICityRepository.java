package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
}
