package com.m6findjobbackend.service.city;

import com.m6findjobbackend.model.City;
import com.m6findjobbackend.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }
}
