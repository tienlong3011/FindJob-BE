package com.m6findjobbackend.service.vacancies;

import com.m6findjobbackend.model.Vacancies;
import com.m6findjobbackend.repository.IVacanciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacanciesService implements IVacanciesService{
    @Autowired
    private IVacanciesRepository vacanciesRepository;

    @Override
    public Iterable<Vacancies> findAll() {
        return vacanciesRepository.findAll();
    }

    @Override
    public Page<Vacancies> findAll(Pageable pageable) {
        return vacanciesRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        vacanciesRepository.deleteById(id);
    }

    @Override
    public Vacancies save(Vacancies vacancies) {
        return vacanciesRepository.save(vacancies);
    }

    @Override
    public Optional<Vacancies> findById(Long id) {
        return vacanciesRepository.findById(id);
    }

}
