package com.claudionogueira.springboot.services.impl;

import java.util.Arrays;

import com.claudionogueira.springboot.model.Role;
import com.claudionogueira.springboot.model.User;
import com.claudionogueira.springboot.repositories.UserRepository;
import com.claudionogueira.springboot.services.UserService;
import com.claudionogueira.springboot.web.dto.UserRegistrationDTO;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(UserRegistrationDTO registrationDTO) {
        User entity = new User(null, registrationDTO.getFirstName(), registrationDTO.getLastName(),
                registrationDTO.getEmail(), registrationDTO.getPassword(), Arrays.asList(new Role(null, "ROLE_USER")));
        repo.save(entity);
    }
}
