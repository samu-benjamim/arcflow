package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.task.TaskCreateRequest;
import com.samu.dev.arcflow.dto.task.TaskResponse;
import com.samu.dev.arcflow.dto.task.TaskUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Task;
import com.samu.dev.arcflow.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    private final PhaseProjectService phaseProjectService;
    private final TaskRepository repository;
    private final ObjectMapper mapper;

    public TaskService(TaskRepository repository, PhaseProjectService phaseProjectService, ObjectMapper mapper) {
        this.repository = repository;
        this.phaseProjectService = phaseProjectService;
        this.mapper = mapper;
    }

    @Transactional
    public TaskResponse createTask(TaskCreateRequest dto, Long phaseId) {
        logger.info("Creating task in phase {}.", phaseId);
        Task taskEntity = mapper.toEntityTask(dto);
        taskEntity.setPhase(mapper.toResoponseConvertProjectPhase(phaseProjectService.findProjectPhaseById(phaseId)));
        return mapper.toResoponseTask(repository.save(taskEntity));
    }

    public TaskResponse findTaskById(Long id) {
        logger.info("Finding task by id {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseTask)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada: " + id));
    }

    @Transactional
    public TaskResponse updateTask(@NotNull TaskUpdateRequest dto, Long id) {
        logger.info("Updating task id {}.", id);
        Task entityDB = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada: " + id));
        mapper.updateEntityTask(dto, entityDB);
        return mapper.toResoponseTask(repository.save(entityDB));
    }

    @Transactional
    public void deleteTask(Long id) {
        logger.info("Deleting task id {}.", id);
        Task task = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada: " + id));
        repository.delete(task);
    }
}
