package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.Account;
import com.m6findjobbackend.model.User;
import com.m6findjobbackend.model.email.MailObject;
import com.m6findjobbackend.security.userprincipal.UserDetailServices;
import com.m6findjobbackend.service.account.AccountService;
import com.m6findjobbackend.service.email.EmailServiceImpl;
import com.m6findjobbackend.service.user.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private IUserSevice userSevice;
    @Autowired
    private UserDetailServices userDetailServices;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userSevice.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> user = userSevice.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Không có user này"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (user.getName() == null) {
            return new ResponseEntity<>(new ResponseMessage("name_null"), HttpStatus.OK);
        }
        User user1 =  userSevice.save(user);
        Account account = accountService.findById(user.getAccount().getId()).get();
        MailObject mailObject1 = new MailObject("findJob@job.com",account.getUsername(), "Account Tinder Windy Verified", "Vui lòng nhấn vào đây để xác nhận tài khoản: " + "\n https://c0821h1.online/active-status/"+account.getId() );
        emailService.sendSimpleMessage(mailObject1);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        Account account = userDetailServices.getCurrentUser();
        if (account.getUsername().equals("Anonymous")) {
            return new ResponseEntity<>(new ResponseMessage("Please login!"), HttpStatus.OK);
        }
        Optional<User> userOptional = userSevice.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userOptional.get().setName(user.getName());
        userOptional.get().setPhone(user.getPhone());
        userSevice.save(userOptional.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        Optional<User> user = userSevice.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userSevice.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
}
