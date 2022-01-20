package com.m6findjobbackend.service.CV;

import com.m6findjobbackend.model.CV;
import com.m6findjobbackend.repository.ICVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CVService implements ICVService {
    @Autowired
    ICVRepository icvRepository;

    @Override
    public Iterable<CV> findAll() {
        return icvRepository.findAll();
    }

    @Override
    public Page<CV> findAll(Pageable pageable) {
        return icvRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        icvRepository.deleteById(id);
    }

    @Override
    public CV save(CV cv) {
        return icvRepository.save(cv);
    }

    @Override
    public Optional<CV> findById(Long id) {
        return icvRepository.findById(id);
    }

    @Override
    public Boolean existsByUserId(Long idUser) {
        return icvRepository.existsByUserId(idUser);
    }
    @Override
    public Optional<CV> findByUserId(Long id) {
        return icvRepository.findByUserId(id);
    }
}
