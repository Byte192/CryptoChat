package com.cryptochat.database;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cryptochat.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> { 
    UserEntity findByUsername(String username);
}
