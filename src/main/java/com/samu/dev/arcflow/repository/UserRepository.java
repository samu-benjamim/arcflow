package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    // Lista membros ativos/inativos de um escritório
    List<User> findAllByOfficeId(Long officeId);

    List<User> findAllByOfficeIdAndActive(Long officeId, boolean active);
}
