package com.m6findjobbackend.service.CV;

import com.m6findjobbackend.model.CV;
import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.service.IGeneralService;

import java.util.Optional;

public interface ICVService extends IGeneralService<CV> {
    Boolean existsByUserId(Long idUser);
    Optional<CV> findByUserId(Long id);
}
