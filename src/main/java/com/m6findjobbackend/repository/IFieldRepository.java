package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFieldRepository extends JpaRepository<Field,Long> {
}
