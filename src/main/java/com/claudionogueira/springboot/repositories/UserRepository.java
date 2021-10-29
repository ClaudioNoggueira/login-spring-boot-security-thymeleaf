package com.claudionogueira.springboot.repositories;

import com.claudionogueira.springboot.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
