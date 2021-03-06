package com.m6findjobbackend.service.workExp;

import com.m6findjobbackend.model.WorkExp;
import com.m6findjobbackend.repository.IWorkExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkExpService implements IWorkExpService{
    @Autowired
    IWorkExpRepository workExpRepository;

    @Override
    public Iterable<WorkExp> findAll() {
        return workExpRepository.findAll();
    }

    @Override
    public Page<WorkExp> findAll(Pageable pageable) {
        return workExpRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        workExpRepository.deleteById(id);
    }

    @Override
    public WorkExp save(WorkExp workExp) {
        return workExpRepository.save(workExp);
    }

    @Override
    public Optional<WorkExp> findById(Long id) {
        return workExpRepository.findById(id);
    }

    @Override
    public Iterable<WorkExp> findAllByCv_Id(Long id) {
        return workExpRepository.findAllByCv_Id(id);
    }

    @Override
    public Boolean existsByCv_Id(Long id) {
        return workExpRepository.existsByCv_Id(id);
    }
}
