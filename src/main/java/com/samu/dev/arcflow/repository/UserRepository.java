package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
