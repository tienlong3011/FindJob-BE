package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkingTimeRepository extends JpaRepository<WorkingTime,Long> {
}
