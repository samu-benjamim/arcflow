package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
