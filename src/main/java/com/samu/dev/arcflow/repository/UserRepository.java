package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
