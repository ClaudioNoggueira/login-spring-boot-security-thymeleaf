package com.claudionogueira.springboot.services;

import com.claudionogueira.springboot.web.dto.UserRegistrationDTO;

public interface UserService {

    void save(UserRegistrationDTO registrationDTO);
}
