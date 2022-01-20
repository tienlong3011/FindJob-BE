package com.m6findjobbackend.service.role;

import com.m6findjobbackend.model.Role;
import com.m6findjobbackend.model.RoleName;
import com.m6findjobbackend.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(RoleName name);
}
