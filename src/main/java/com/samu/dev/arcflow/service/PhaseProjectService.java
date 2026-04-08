package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.project.ProjectCreateRequest;
import com.samu.dev.arcflow.dto.project.ProjectSummaryResponse;
import com.samu.dev.arcflow.dto.project.ProjectUpdateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseCreateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseResponse;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Project;
import com.samu.dev.arcflow.model.ProjectPhase;
import com.samu.dev.arcflow.repository.ProjectPhaseRepository;
import com.samu.dev.arcflow.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhaseProjectService {

    private final Logger logger = LoggerFactory.getLogger(PhaseProjectService.class.getName());

    private final ProjectService projectService;

    private final ProjectPhaseRepository repository;


    private final ObjectMapper mapper;

    public PhaseProjectService(ProjectService projectService, ProjectPhaseRepository repository, ObjectMapper mapper) {
        this.projectService = projectService;
        this.repository = repository;
        this.mapper = mapper;
    }


    @Transactional
    public ProjectPhaseResponse createProjectPhase(ProjectPhaseCreateRequest projectPhaseDTO, Long projectId) {
        logger.info("Create one Project Phase.");
        ProjectPhase projectPhaseEntity = mapper.toEntityProjectPhase(projectPhaseDTO);
        projectPhaseEntity.setProject(mapper.toResoponseConvertProject(projectService.findProjectById(projectId)));
        return mapper.toResoponseProjectPhase(repository.save(projectPhaseEntity));
    }

    public ProjectPhaseResponse findProjectPhaseById(Long id) {
        logger.info("Finding one Project Phase by id: {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseProjectPhase)
                .orElseThrow(()-> new EntityNotFoundException("Phase not found id"));
    }

    @Transactional
    public ProjectPhaseResponse updateProject(@NotNull ProjectPhaseUpdateRequest projectPhaseDTO, Long id) {
        logger.info("Update one Project Phase by id: {}.", id);
        ProjectPhase entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Phase not found id"));
        mapper.updateEntityProjectPhase(projectPhaseDTO, entityDB);
        return mapper.toResoponseProjectPhase(repository.save(entityDB));
    }

    @Transactional
    public void deleteProject(Long id) {
        logger.info("Deleting one Project Phase by id: {}.", id);
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }



}
