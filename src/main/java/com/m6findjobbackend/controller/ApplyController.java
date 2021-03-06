package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.request.ApplyJob;
import com.m6findjobbackend.dto.request.ForwardApply;
import com.m6findjobbackend.dto.response.ApplyShowAll;
import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.*;
import com.m6findjobbackend.model.email.MailObject;
import com.m6findjobbackend.service.CV.ICVService;
import com.m6findjobbackend.service.account.AccountService;
import com.m6findjobbackend.service.apply.IApplyService;
import com.m6findjobbackend.service.company.CompanyService;
import com.m6findjobbackend.service.email.EmailService;
import com.m6findjobbackend.service.email.EmailServiceImpl;
import com.m6findjobbackend.service.recruitmentNew.IRecruitmentNewService;
import com.m6findjobbackend.service.user.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/applies")
public class ApplyController {
    @Autowired
    private IApplyService applyService;

    @Autowired
    private IRecruitmentNewService recruitmentNewService;

    @Autowired
    private IUserSevice userSevice;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ICVService icvService;

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping
    public ResponseEntity<?> createApply(@RequestBody ApplyJob applyJob) {
        System.out.println(applyService.existsByUserIdAndRecuitmentNewId(applyJob.getUserId(),applyJob.getRecuitmentNewId()));
        if(applyService.existsByUserIdAndRecuitmentNewId(applyJob.getUserId(),applyJob.getRecuitmentNewId())){
            return new ResponseEntity<>(new ResponseMessage("MATCH"), HttpStatus.OK);
        }
        if(icvService.existsByUserId(applyJob.getUserId())){
            LocalDate now = LocalDate.now();
            RecuitmentNew recuitmentNew = recruitmentNewService.findById(applyJob.getRecuitmentNewId()).get();
            User user = userSevice.findById(applyJob.getUserId()).get();
            Apply apply = new Apply();
            apply.setDate(now);
            apply.setStatus(Status.WAIT);
            apply.setUser(user);
            apply.setRecuitmentNew(recuitmentNew);
            applyService.save(apply);
            return new ResponseEntity<>(new ResponseMessage("CREATE"), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new ResponseMessage("CREATE_FAIL"), HttpStatus.OK);
        }
    }
    @PostMapping("/forward")
    public ResponseEntity<?> forwardApply(@RequestBody ForwardApply forwardApply){
        Company companyCurrent = companyService.findById(forwardApply.getIdCompany()).get();
        User userCurrent = userSevice.findById(forwardApply.getIdUser()).get();
        MailObject mailObject = new MailObject("findJob@job.com",userCurrent.getAccount().getUsername(), "Th??ng b??o tuy???n d???ng", "B???n " + userCurrent.getName() + " ???? ???ng tuy???n v??o c??ng ty " +companyCurrent.getName() + " th??nh c??ng. Vui l??ng ch??? mail t??? c??ng ty ????? x??c nh???n" );
        emailService.sendSimpleMessage(mailObject);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/forwardSuccess")
    public ResponseEntity<?> forwardApplySuccess(@RequestBody ForwardApply forwardApply){
        Company companyCurrent = companyService.findById(forwardApply.getIdCompany()).get();
        User userCurrent = userSevice.findById(forwardApply.getIdUser()).get();
        LocalDate dateApply = LocalDate.now().plusDays(3) ;
        MailObject mailObject = new MailObject("findJob@job.com",userCurrent.getAccount().getUsername(), "Th??ng b??o tuy???n d???ng", "C??ng ty " + companyCurrent.getName() + " ???? ch???p nh???n ????n ???ng tuy???n c???a b???n " + userCurrent.getName() + ". L???ch ph???ng v???n c???a b???n v???i c??ng ty v??o ng??y " + dateApply +". H??y li??n h??? v???i c??ng ty b???n ???ng tuy???n ????? bi???t th??m chi ti???t !!");
        emailService.sendSimpleMessage(mailObject);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @GetMapping("/findAllByCompanyID/{id}")
    public ResponseEntity<?> findAllByCompany(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,@PathVariable Long id) {
        Page<ApplyShowAll> list = applyService.findAllByCompanyId(pageable, id);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/showAllApply/{id}")
    public ResponseEntity<?>showAllApplyById(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable Long id){
        Page<Apply> list = applyService.findAllByUserId(pageable,id);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/changeStatusApply")
    public ResponseEntity<?> changeStatusApply(@RequestBody ChangeStatusApply changeStatusApply ){
        Apply apply = applyService.findById(changeStatusApply.getId()).get();
        boolean check = false;
        List<Apply> applyList = (List<Apply>) applyService.findAll();
        for (Apply apply1 : applyList ) {
            if(apply1.getUser().getId() == apply.getUser().getId() && apply1.getRecuitmentNew().getCompany().getId() == apply.getRecuitmentNew().getCompany().getId()){
                String statusold = String.valueOf(apply1.getStatus());
                System.out.println(Status.APCEPT);
                System.out.println(statusold);
                if(statusold.equals("APCEPT")){
                    check = true;
                }
            }
        }
        if(changeStatusApply.getStatus() == 1){
            if(check){
                return new ResponseEntity<>(new ResponseMessage("Nh??n vi??n ???? ???????c apcept b???i c??ng ty kh??c"), HttpStatus.OK);
            }
            apply.setStatus(Status.APCEPT);
            applyService.save(apply);
            Company companyCurrent = apply.getRecuitmentNew().getCompany();
            User userCurrent = apply.getUser();
            LocalDate dateApply = LocalDate.now().plusDays(3) ;
            MailObject mailObject = new MailObject("findJob@job.com",userCurrent.getAccount().getUsername(), "Th??ng b??o tuy???n d???ng", "C??ng ty " + companyCurrent.getName() + " ???? ch???p nh???n ????n ???ng tuy???n c???a b???n " + userCurrent.getName() + ". L???ch ph???ng v???n c???a b???n v???i c??ng ty v??o ng??y " + dateApply +". H??y li??n h??? v???i c??ng ty b???n ???ng tuy???n ????? bi???t th??m chi ti???t !!");
            emailService.sendSimpleMessage(mailObject);
            return new ResponseEntity<>(new ResponseMessage("Nh??n vi??n ???? ???????c apcept th??nh c??ng"), HttpStatus.OK);

        }
        if(changeStatusApply.getStatus() == 0){
            apply.setStatus(Status.REJECT);
            applyService.save(apply);
            Company companyCurrent = apply.getRecuitmentNew().getCompany();
            User userCurrent = apply.getUser();
            MailObject mailObject = new MailObject("findJob@job.com",userCurrent.getAccount().getUsername(), "Th??ng b??o tuy???n d???ng", "C??ng ty " + companyCurrent.getName() + " ???? t??? ch???i ????n ???ng tuy???n c???a b???n " + userCurrent.getName() + ". Ch??c b???n may m???n l???n sau ");
            emailService.sendSimpleMessage(mailObject);
            return new ResponseEntity<>(new ResponseMessage("B???n ???? t??? ch???i th??nh c??ng"), HttpStatus.OK);

        }
        if(changeStatusApply.getStatus() == 2){
            apply.setStatus(Status.WAIT);
            applyService.save(apply);
            return new ResponseEntity<>(new ResponseMessage("B???n ???? ?????i tr???ng th??i th??nh c??ng"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseMessage("B???n ???? ?????i tr???ng th??i th???t b???i"), HttpStatus.OK);
        }
    }
}
