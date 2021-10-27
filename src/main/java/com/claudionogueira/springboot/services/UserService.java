package com.claudionogueira.springboot.services;

import com.claudionogueira.springboot.web.dto.UserRegistrationDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(UserRegistrationDTO registrationDTO);
}
