package com.m6findjobbackend.service.company;

import com.m6findjobbackend.dto.response.CompanyRecruitmentNeed;
import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company> {
    Optional<Company> findAllByAccount_Id(Long id);
    Boolean existsByName(String name);
    List<Company> findCompanyByStatus(Integer stt);
    List<CompanyRecruitmentNeed> findCompanyByRecuitmentNew();

}
