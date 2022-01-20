package com.m6findjobbackend.repository;

import com.m6findjobbackend.dto.request.SearchJob;
import com.m6findjobbackend.dto.response.RecuitmentNewDTO;

import java.util.List;

public interface IRecruitmentNewDAO {
    List<RecuitmentNewDTO> findJob(SearchJob searchJob);
}
