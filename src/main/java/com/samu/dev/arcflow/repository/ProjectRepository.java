package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}
