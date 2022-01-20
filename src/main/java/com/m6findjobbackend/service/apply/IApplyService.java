package com.m6findjobbackend.service.apply;

import com.m6findjobbackend.dto.response.ApplyShowAll;
import com.m6findjobbackend.model.Apply;
import com.m6findjobbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IApplyService  extends IGeneralService<Apply> {
    Page<ApplyShowAll> findAllByCompanyId(Pageable page, @Param("idCompany") Long id);
    Page<Apply> findAllByUserId(Pageable pageable, Long id);
    boolean existsByUserIdAndRecuitmentNewId(Long userID, Long recuitmentID);

}
