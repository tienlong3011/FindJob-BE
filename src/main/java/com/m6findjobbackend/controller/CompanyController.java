package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.request.EditCompany;
import com.m6findjobbackend.dto.request.StatusRequest;
import com.m6findjobbackend.dto.response.CompanyRecruitmentNeed;
import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.Account;
import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.model.Status;
import com.m6findjobbackend.security.userprincipal.UserDetailServices;
import com.m6findjobbackend.service.city.CityService;
import com.m6findjobbackend.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("company")
@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    UserDetailServices userDetailServices;

    @Autowired
    CityService cityService;

    @PostMapping("")
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
//        Account account = userDetailServices.getCurrentUser();
//        if(account.getUsername().equals("Anonymous")){
//            return new ResponseEntity<>(new ResponseMessage("Please login!"), HttpStatus.OK);
//        }

        if (companyService.existsByName(company.getName())) {
            return new ResponseEntity<>(new ResponseMessage("no_name_category"), HttpStatus.OK);
        }
        //tao codeCompany
        String nameex = company.getName().substring(0, 3);
        int min = 1000;
        int max = 9999;
        String codeCompany = String.valueOf((int) Math.floor(Math.round((Math.random() * (max - min + 1) + min))));
        System.out.println(codeCompany);
        company.setCodeCompany(nameex + company.getAccount().getId() + codeCompany);
        //
        company.setStatusCompany(Status.HOT);
        if (company.getAvatar() == null) {
            return new ResponseEntity<>(new ResponseMessage("no_avatar_category"), HttpStatus.OK);
        }
        companyService.save(company);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> showListCompany() {
        Iterable<Company> companyList = companyService.findAll();
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody EditCompany editCompany) {
        Account account = userDetailServices.getCurrentUser();
        if (account.getUsername().equals("Anonymous")) {
            return new ResponseEntity<>(new ResponseMessage("Please login!"), HttpStatus.OK);
        }
        Optional<Company> company1 = companyService.findById(id);
        if (!company1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean check = false;
        if (companyService.existsByName(editCompany.getName())) {
            check = true;
        }

        if (editCompany.getAvatar() != null) {
            company1.get().setAvatar(editCompany.getAvatar());
        }
        if (editCompany.getName() != null) {
            company1.get().setName(editCompany.getName());
        }
        if (editCompany.getDescription() != null) {
            company1.get().setDescription(editCompany.getDescription());
        }
        if (editCompany.getAddress() != null) {
            company1.get().setAddress(editCompany.getAddress());
        }
        if (editCompany.getEmployeeQuantity() != null) {
            company1.get().setEmployeeQuantity(editCompany.getEmployeeQuantity());
        }
        if (editCompany.getLinkMap() != null) {
            company1.get().setLinkMap(editCompany.getLinkMap());
        }
        if (editCompany.getPhone() != null) {
            company1.get().setPhone(editCompany.getPhone());
        }
        //tao codeCompany
        String nameex = company1.get().getName().substring(0, 3);
        int min = 1000;
        int max = 9999;
        String codeCompany = String.valueOf((int) Math.floor(Math.round((Math.random() * (max - min + 1) + min))));
        company1.get().setCodeCompany(nameex + company1.get().getAccount().getId() + codeCompany);
        companyService.save(company1.get());
        if (check == true) {
            return new ResponseEntity<>(new ResponseMessage("trung ten roi"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PutMapping("/change_status")
    public ResponseEntity<?> updateCompany1(@PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        Account account = userDetailServices.getCurrentUser();
        if (account.getUsername().equals("Anonymous")) {
            return new ResponseEntity<>(new ResponseMessage("Please login"), HttpStatus.OK);
        }
        Optional<Company> company1 = companyService.findById(id);
        if (!company1.isPresent()) {
            if (statusRequest.getStatus() == 1) {
                company1.get().setStatusCompany(Status.ACTIVE);
            }
            if (statusRequest.getStatus() == 2) {
                company1.get().setStatusCompany(Status.NON_ACTIVE);
            }
            if (statusRequest.getStatus() == 3) {
                company1.get().setStatusCompany(Status.LOCK);
            }
            if (statusRequest.getStatus() == 4) {
                company1.get().setStatusCompany(Status.UNLOCK);
            }
            if (statusRequest.getStatus() == 5) {
                company1.get().setStatusCompany(Status.HOT);
            }
            if (statusRequest.getStatus() == 6) {
                company1.get().setStatusCompany(Status.WAIT);
            }
            if (statusRequest.getStatus() == 7) {
                company1.get().setStatusCompany(Status.REJECT);
            }
            companyService.save(company1.get());
        }
        return new ResponseEntity<>(new ResponseMessage("Yes"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCompany(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable Integer status){
        List<Company> companyList = companyService.findCompanyByStatus(status);
        return new ResponseEntity<>(companyList,HttpStatus.OK);
    }

    @GetMapping("/findByRecuitmentNewNeed")
    public ResponseEntity<?> findByRecuitmentNewNeed(){
        List<CompanyRecruitmentNeed> recruitmentNeedList = companyService.findCompanyByRecuitmentNew();
        return new ResponseEntity<>(recruitmentNeedList,HttpStatus.OK);
    }

}
