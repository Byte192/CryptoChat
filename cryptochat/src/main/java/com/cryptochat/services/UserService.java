package com.cryptochat.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cryptochat.database.UserRepository;
import com.cryptochat.entities.UserEntity;

public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registeUser(String username, String password) {
        // Generate a unique salt for the user
        String salt = generateSalt();

        // Hash the password with the salt bcrypt
        String hashedPassword = passwordEncoder.encode(password + salt);

        // Save username, hashedPassword, and salt in the database
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setSalt(salt);

        userRepository.save(user);
    }

    private String generateSalt() {
        return null;
    }
}
