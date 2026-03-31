package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
