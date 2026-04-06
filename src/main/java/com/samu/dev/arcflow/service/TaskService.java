package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseCreateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseResponse;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseUpdateRequest;
import com.samu.dev.arcflow.dto.task.TaskCreateRequest;
import com.samu.dev.arcflow.dto.task.TaskResponse;
import com.samu.dev.arcflow.dto.task.TaskUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.ProjectPhase;
import com.samu.dev.arcflow.model.Task;
import com.samu.dev.arcflow.repository.ProjectPhaseRepository;
import com.samu.dev.arcflow.repository.ProjectRepository;
import com.samu.dev.arcflow.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    private final PhaseProjectService phaseProjectService;

    private final TaskRepository repository;


    private final ObjectMapper mapper;

    public TaskService(ProjectRepository projectRepository, TaskRepository repository, OfficeService officeService, ClientService clientService, ProjectRepository projectRepository1, ProjectService projectService, PhaseProjectService phaseProjectService, ProjectPhaseRepository repository1, ObjectMapper mapper) {
        this.phaseProjectService = phaseProjectService;
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskResponse createTask(TaskCreateRequest projectPhaseDTO, Long phaseId) {
        logger.info("Create one Project.");
        Task taskEntity = mapper.toEntityTask(projectPhaseDTO);
        taskEntity.setPhase(mapper.toResoponseConvertProjectPhase(phaseProjectService.findProjectPhaseById(phaseId)));
        return mapper.toResoponseTask(repository.save(taskEntity));
    }

    public TaskResponse findTaskById(Long id) {
        logger.info("Finding one ProjectPhase by id.");
        return repository.findById(id)
                .map(mapper::toResoponseTask)
                .orElseThrow(()-> new EntityNotFoundException("Task not found id"));
    }

    public TaskResponse updateTask(@NotNull TaskUpdateRequest projectPhaseDTO, Long id) {
        logger.info("Update one Task.");
        Task entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task not found id"));
        mapper.updateEntityTask(projectPhaseDTO, entityDB);
        return mapper.toResoponseTask(repository.save(entityDB));
    }

    public void deleteTask(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }



}
