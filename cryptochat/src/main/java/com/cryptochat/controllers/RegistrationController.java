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

        // Validate username and passwors
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return "Invalid username or password";
        }

        // Check if the usernae is already registered
        if (userService.isUserNameTaken(username)) {
            return "Username is already taken. Please choose a different username.";
        }

        // Generate salt and hash the password
        String salt = userService.generateSalt();
        String hashedPassword = userService.hashPassword(password, salt);

        // Register the user
        userService.registeUser(username, hashedPassword, salt);

        return "User registerd sucessfully!";
    }
}
