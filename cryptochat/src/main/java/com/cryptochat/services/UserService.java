package com.cryptochat.services;

import java.security.SecureRandom;
import java.util.Base64;

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

    public void registeUser(String username, String hashedPassword, String salt) {

        // Save username, hashedPassword, and salt in the database
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setSalt(salt);

        userRepository.save(user);
    }

    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];

        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public boolean isUserNameTaken(String username) {
        UserEntity existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }

    public String hashPassword(String password, String salt) {
        // Combine password and salt, and the hash usng BCrypt
        String passwordWithSalt = password + salt;
        return passwordEncoder.encode(passwordWithSalt);
    }
}
