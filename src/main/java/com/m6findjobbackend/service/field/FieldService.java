package com.m6findjobbackend.service.field;

import com.m6findjobbackend.model.Field;
import com.m6findjobbackend.repository.IFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FieldService implements IFieldService {
    @Autowired
    private IFieldRepository fieldRepository;

    @Override
    public Iterable<Field> findAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Page<Field> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        fieldRepository.deleteById(id);
    }

    @Override
    public Field save(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldRepository.findById(id);
    }
}
