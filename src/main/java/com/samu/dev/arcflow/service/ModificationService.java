package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.modification.ModificationCreateRequest;
import com.samu.dev.arcflow.dto.modification.ModificationResponse;
import com.samu.dev.arcflow.dto.modification.ModificationUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Modification;
import com.samu.dev.arcflow.repository.ModificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ModificationService {

    private final Logger logger = LoggerFactory.getLogger(ModificationService.class.getName());

    private final ProjectService projectService;

    private final ModificationRepository repository;


    private final ObjectMapper mapper;

    public ModificationService(ProjectService projectService, ModificationRepository repository, ObjectMapper mapper) {
        this.projectService = projectService;
        this.repository = repository;
        this.mapper = mapper;
    }

    public ModificationResponse createModification(ModificationCreateRequest documentDTO, Long projectId) {
        logger.info("Create one Modification Revision.");
        Modification taskEntity = mapper.toEntityModification(documentDTO);
        taskEntity.setProject(mapper.toResoponseConvertProject(projectService.findProjectById(projectId)));
        return mapper.toResoponseModification(repository.save(taskEntity));
    }

    public ModificationResponse findModificationById(Long id) {
        logger.info("Finding one ProjectPhase by id.");
        return repository.findById(id)
                .map(mapper::toResoponseModification)
                .orElseThrow(()-> new EntityNotFoundException("Modification Revision not found id"));
    }

    public ModificationResponse updateModification(@NotNull ModificationUpdateRequest ModificationDTO, Long id) {
        logger.info("Update one Office.");
        Modification entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("client Approval not found id"));
        mapper.updateEntityModification(ModificationDTO, entityDB);
        return mapper.toResoponseModification(repository.save(entityDB));
    }

    public void deleteModification(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }
}
