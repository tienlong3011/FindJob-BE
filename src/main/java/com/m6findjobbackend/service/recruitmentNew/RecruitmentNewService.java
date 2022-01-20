package com.m6findjobbackend.service.recruitmentNew;

import com.m6findjobbackend.dto.request.SearchJob;
import com.m6findjobbackend.dto.response.PageResponse;
import com.m6findjobbackend.dto.response.RecuitmentNewDTO;
import com.m6findjobbackend.model.RecuitmentNew;
import com.m6findjobbackend.repository.IRecruitmentNewDAO;
import com.m6findjobbackend.repository.IRecruitmentNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentNewService implements IRecruitmentNewService {
    @Autowired
    IRecruitmentNewRepository recruitmentNewRepository;

    @Autowired
    IRecruitmentNewDAO recruitmentNewDAO;

    @Override
    public Iterable<RecuitmentNew> findAll() {
        return recruitmentNewRepository.findAll();
    }

    @Override
    public Page<RecuitmentNew> findAll(Pageable pageable) {
        return recruitmentNewRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        recruitmentNewRepository.deleteById(id);
    }

    @Override
    public RecuitmentNew save(RecuitmentNew recuitmentNew) {
        return recruitmentNewRepository.save(recuitmentNew);
    }

    @Override
    public Optional<RecuitmentNew> findById(Long id) {
        return recruitmentNewRepository.findById(id);
    }

    @Override
    public List<RecuitmentNew> findAllByCompany_Id(Long id) {
        return recruitmentNewRepository.findAllByCompany_Id(id);
    }

    @Override
    public PageResponse searchByObj(SearchJob searchJob) {
        List<RecuitmentNewDTO> list = recruitmentNewDAO.findJob(searchJob);
        PageResponse pageResponse = new PageResponse();
        pageResponse.setData(list);
        pageResponse.setTotalRecord(recruitmentNewRepository.countTotalRecords(searchJob.getTitle(),
                searchJob.getCityId(),
                searchJob.getFieldId(),
                searchJob.getCompanyId(),
                searchJob.getVacancies(),
                searchJob.getWorkingTimeId(),
                searchJob.getSalary()));

        return pageResponse;
    }


}
