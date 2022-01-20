package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.Vacancies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVacanciesRepository extends JpaRepository<Vacancies,Long> {
}
