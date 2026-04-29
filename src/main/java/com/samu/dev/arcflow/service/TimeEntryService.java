package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.timeentry.TimeEntryCreateRequest;
import com.samu.dev.arcflow.dto.timeentry.TimeEntryResponse;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.TimeEntry;
import com.samu.dev.arcflow.model.User;
import com.samu.dev.arcflow.repository.TimeEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimeEntryService {

    private final Logger logger = LoggerFactory.getLogger(TimeEntryService.class.getName());

    private final TaskService taskService;
    private final TimeEntryRepository repository;
    private final ObjectMapper mapper;

    public TimeEntryService(TimeEntryRepository repository, TaskService taskService, ObjectMapper mapper) {
        this.taskService = taskService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public TimeEntryResponse createTimeEntry(TimeEntryCreateRequest dto, Long taskId, User currentUser) {
        logger.info("Creating time entry for task {} by user {}.", taskId, currentUser.getId());
        TimeEntry entry = mapper.toEntityTimeEntry(dto);
        entry.setTask(mapper.toResoponseConvertTask(taskService.findTaskById(taskId)));
        entry.setUser(currentUser);
        return mapper.toResoponseTimeEntry(repository.save(entry));
    }

    public TimeEntryResponse findTimeEntryById(Long id) {
        logger.info("Finding time entry by id {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseTimeEntry)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento de horas não encontrado: " + id));
    }

    @Transactional
    public void deleteTimeEntry(Long id) {
        logger.info("Deleting time entry id {}.", id);
        TimeEntry entry = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento de horas não encontrado: " + id));
        repository.delete(entry);
    }
}
