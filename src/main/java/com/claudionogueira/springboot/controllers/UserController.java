package com.claudionogueira.springboot.controllers;

import com.claudionogueira.springboot.services.UserService;
import com.claudionogueira.springboot.web.dto.UserRegistrationDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/registration")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
        service.save(registrationDTO);
        return "redirect:/registration?success";
    }
}
