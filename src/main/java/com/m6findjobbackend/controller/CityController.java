package com.m6findjobbackend.controller;

import com.m6findjobbackend.model.City;
import com.m6findjobbackend.service.city.CityService;
import com.m6findjobbackend.service.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private FieldService fieldService;

    @GetMapping("/showAll")
    public ResponseEntity<Iterable<City>> showAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createCity")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<City> editCity(@PathVariable Long id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (city.getId() != null) {
                city.setId(id);
            }
            return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.deleteById(id);

        return new ResponseEntity<>(cityOptional.get(),HttpStatus.OK);
    }

//    @GetMapping("/findCityAndField")
//    public ResponseEntity<City>
}
