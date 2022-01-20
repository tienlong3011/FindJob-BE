package com.m6findjobbackend.service.workExp;

import com.m6findjobbackend.model.WorkExp;
import com.m6findjobbackend.service.IGeneralService;

public interface IWorkExpService extends IGeneralService<WorkExp> {
    Iterable<WorkExp> findAllByCv_Id(Long id);
    Boolean existsByCv_Id(Long id);
}
