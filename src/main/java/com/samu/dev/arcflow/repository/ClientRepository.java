package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByName(String name);
}
