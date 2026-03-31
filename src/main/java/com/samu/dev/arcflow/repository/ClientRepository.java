package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
