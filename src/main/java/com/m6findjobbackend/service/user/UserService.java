package com.m6findjobbackend.service.user;

import com.m6findjobbackend.dto.request.UsernameAndPasswordUser;
import com.m6findjobbackend.model.User;
import com.m6findjobbackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserSevice{
    @Autowired
    IUserRepository userRepository;
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByAccount_Id(Long id) {
        return userRepository.findByAccount_Id(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public UsernameAndPasswordUser findUsernameAndPassword(Long id) {
        return null;
    }
}
