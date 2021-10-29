package com.claudionogueira.springboot.services.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.claudionogueira.springboot.model.Role;
import com.claudionogueira.springboot.model.User;
import com.claudionogueira.springboot.repositories.UserRepository;
import com.claudionogueira.springboot.services.UserService;
import com.claudionogueira.springboot.web.dto.UserRegistrationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(UserRegistrationDTO registrationDTO) {
        User entity = new User(null, registrationDTO.getFirstName(), registrationDTO.getLastName(),
                registrationDTO.getEmail(), passwordEncoder.encode(registrationDTO.getPassword()),
                Arrays.asList(new Role(null, "ROLE_USER")));
        repo.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nome de usuário ou senha inválidos.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                this.mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
