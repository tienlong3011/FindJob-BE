package com.m6findjobbackend.service.recruitmentNew;

import com.m6findjobbackend.dto.request.SearchJob;
import com.m6findjobbackend.dto.response.PageResponse;
import com.m6findjobbackend.dto.response.RecuitmentNewDTO;
import com.m6findjobbackend.model.RecuitmentNew;
import com.m6findjobbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRecruitmentNewService extends IGeneralService<RecuitmentNew> {
    List<RecuitmentNew> findAllByCompany_Id(Long id);

    PageResponse searchByObj(SearchJob searchJob);
}
