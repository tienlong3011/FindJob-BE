package com.m6findjobbackend.service.account;

import com.m6findjobbackend.model.Account;
import com.m6findjobbackend.service.IGeneralService;

import java.util.Optional;

public interface IAccountService extends IGeneralService<Account> {
    Optional<Account> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
}
