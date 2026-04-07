package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.timeentry.TimeEntryCreateRequest;
import com.samu.dev.arcflow.dto.timeentry.TimeEntryResponse;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.TimeEntry;
import com.samu.dev.arcflow.repository.ProjectPhaseRepository;
import com.samu.dev.arcflow.repository.ProjectRepository;
import com.samu.dev.arcflow.repository.TimeEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TimeEntryService {

    private final Logger logger = LoggerFactory.getLogger(TimeEntryService.class.getName());

    private final TaskService taskService;

    private final TimeEntryRepository repository;


    private final ObjectMapper mapper;

    public TimeEntryService(ProjectRepository projectRepository, TimeEntryRepository repository, OfficeService officeService, ClientService clientService, ProjectRepository projectRepository1, ProjectService projectService, PhaseProjectService phaseProjectService, ProjectPhaseRepository repository1, TaskService taskService, ObjectMapper mapper) {
        this.taskService = taskService;
        this.repository = repository;
        this.mapper = mapper;
    }

    public TimeEntryResponse createTimeEntry(TimeEntryCreateRequest projectPhaseDTO, Long phaseId) {
        logger.info("Create one Project.");
        TimeEntry taskEntity = mapper.toEntityTimeEntry(projectPhaseDTO);
        taskEntity.setTask(mapper.toResoponseConvertTask(taskService.findTaskById(phaseId)));
        return mapper.toResoponseTimeEntry(repository.save(taskEntity));
    }

    public TimeEntryResponse findTimeEntryById(Long id) {
        logger.info("Finding one ProjectPhase by id.");
        return repository.findById(id)
                .map(mapper::toResoponseTimeEntry)
                .orElseThrow(()-> new EntityNotFoundException("TimeEntry not found id"));
    }

    public void deleteTimeEntry(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }



}
