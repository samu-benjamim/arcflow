package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Modification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModificationRepository extends JpaRepository<Modification, Long> {}
