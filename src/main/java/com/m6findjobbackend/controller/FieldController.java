package com.m6findjobbackend.controller;

import com.m6findjobbackend.model.Field;
import com.m6findjobbackend.service.field.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/field")
public class FieldController {
    @Autowired
    private IFieldService fieldService;

    @GetMapping("/showAll")
    public ResponseEntity<Iterable<Field>> showAll(){
        return new ResponseEntity<>(fieldService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Field> findById(@PathVariable Long id){
        Optional<Field> fieldOptional = fieldService.findById(id);
        if (!fieldOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(fieldOptional.get(),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Field> createField(@RequestBody Field field){
        return new ResponseEntity<>(fieldService.save(field), HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Field> editField(@PathVariable Long id, @RequestBody Field field){
        Optional<Field> fieldOptional = fieldService.findById(id);
        if (!fieldOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (field.getId() != null){
                field.setId(id);
            }
            return new ResponseEntity<>(fieldService.save(field),HttpStatus.OK);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Field> deleteField(@PathVariable Long id){
        Optional<Field> fieldOptional = fieldService.findById(id);
        if (!fieldOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            fieldService.deleteById(id);
        return new ResponseEntity<>(fieldOptional.get(),HttpStatus.OK);
    }
}
