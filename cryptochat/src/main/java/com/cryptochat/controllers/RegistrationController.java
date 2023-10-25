package com.cryptochat.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cryptochat.services.UserService;

@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // TODO registration logic

        return "User registerd sucessfully!";
    }
}
