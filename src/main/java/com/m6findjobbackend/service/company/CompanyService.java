package com.m6findjobbackend.service.company;

import com.m6findjobbackend.dto.response.CompanyRecruitmentNeed;
import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.repository.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private ICompanyRepository iCompanyRepository;

    @Override
    public Iterable<Company> findAll() {
        return iCompanyRepository.findAll();
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return iCompanyRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        iCompanyRepository.deleteById(id);
    }

    @Override
    public Company save(Company company) {
        return iCompanyRepository.save(company);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return iCompanyRepository.findById(id);
    }

    @Override
    public Optional<Company> findAllByAccount_Id(Long id) {
        return iCompanyRepository.findAllByAccount_Id(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return iCompanyRepository.existsByName(name);
    }

    @Override
    public List<Company> findCompanyByStatus(Integer stt) {
        return iCompanyRepository.findCompanyByStatus(stt);
    }

    @Override
    public List<CompanyRecruitmentNeed> findCompanyByRecuitmentNew() {
        return iCompanyRepository.findCompanyByRecuitmentNew();
    }
}
