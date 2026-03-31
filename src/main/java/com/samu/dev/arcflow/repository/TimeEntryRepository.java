package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {}
