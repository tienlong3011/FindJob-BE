package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISkillRepository extends JpaRepository<Skill,Long> {
    Iterable<Skill> findAllByCv_Id(Long id);
    Boolean existsByCv_Id(Long id);
    boolean existsById(Long id);
}
