package com.m6findjobbackend.service.user;

import com.m6findjobbackend.dto.request.UsernameAndPasswordUser;
import com.m6findjobbackend.model.User;
import com.m6findjobbackend.service.IGeneralService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserSevice extends IGeneralService<User> {
    Optional<User> findByAccount_Id(Long id);
    Boolean existsByName(String name);
    UsernameAndPasswordUser findUsernameAndPassword(Long id);
}
