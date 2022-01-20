package com.m6findjobbackend.service.role;

import com.m6findjobbackend.model.Role;
import com.m6findjobbackend.model.RoleName;
import com.m6findjobbackend.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Role save(Role product) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
