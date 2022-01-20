package com.m6findjobbackend.service.email;


import com.m6findjobbackend.model.email.MailObject;

public interface EmailService {
    void sendSimpleMessage(MailObject mail);

}
