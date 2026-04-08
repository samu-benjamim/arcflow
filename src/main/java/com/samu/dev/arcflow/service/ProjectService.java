package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.project.ProjectCreateRequest;
import com.samu.dev.arcflow.dto.project.ProjectSummaryResponse;
import com.samu.dev.arcflow.dto.project.ProjectUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Project;
import com.samu.dev.arcflow.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectService.class.getName());

    private final ProjectRepository repository;

    private final OfficeService officeService;

    private final ClientService clientService;

    private final ObjectMapper mapper;

    public ProjectService(ProjectRepository projectRepository, ProjectRepository repository, OfficeService officeService, ClientService clientService, ObjectMapper mapper) {
        this.repository = repository;
        this.officeService = officeService;
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @Transactional
    public ProjectSummaryResponse createProject(ProjectCreateRequest projectDTO, Long officeId, Long clientId) {
        logger.info("Create one Project.");
        Project projectEntity = mapper.toEntityProject(projectDTO);
        projectEntity.setOffice(mapper.toResoponseConvertOffice(officeService.findOfficeById(officeId)));
        projectEntity.setClient(mapper.toResoponseConvertClient(clientService.findClientById(clientId)));
        return mapper.toResoponseProject(repository.save(projectEntity));
    }

    public ProjectSummaryResponse findProjectById(Long id) {
        logger.info("Finding one Project by id: {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseProject)
                .orElseThrow(()-> new EntityNotFoundException("Project not found id"));
    }

    @Transactional
    public ProjectSummaryResponse updateProject(@NotNull ProjectUpdateRequest projectDTO, Long id) {
        logger.info("Update one Project by id: {}.", id);
        Project entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Project not found id"));
        mapper.updateEntityProject(projectDTO, entityDB);
        return mapper.toResoponseProject(repository.save(entityDB));
    }

    @Transactional
    public void deleteProject(Long id) {
        logger.info("Deleting one Project by id: {}.", id);
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }



}
