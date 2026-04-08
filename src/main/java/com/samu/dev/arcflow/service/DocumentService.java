package com.samu.dev.arcflow.service;


import com.samu.dev.arcflow.dto.document.DocumentCreateRequest;
import com.samu.dev.arcflow.dto.document.DocumentResponse;
import com.samu.dev.arcflow.dto.document.DocumentUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Document;
import com.samu.dev.arcflow.repository.DocumentRepository;
import com.samu.dev.arcflow.repository.ProjectPhaseRepository;
import com.samu.dev.arcflow.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentService {

    private final Logger logger = LoggerFactory.getLogger(DocumentService.class.getName());

    private final PhaseProjectService phaseProjectService;

    private final DocumentRepository repository;


    private final ObjectMapper mapper;

    public DocumentService(PhaseProjectService phaseProjectService, DocumentRepository repository, ObjectMapper mapper) {
        this.phaseProjectService = phaseProjectService;
        this.repository = repository;
        this.mapper = mapper;
    }


    @Transactional
    public DocumentResponse createDocument(DocumentCreateRequest documentDTO, Long phaseId) {
        logger.info("Create one Document.");
        Document taskEntity = mapper.toEntityDocument(documentDTO);
        taskEntity.setPhase(mapper.toResoponseConvertProjectPhase(phaseProjectService.findProjectPhaseById(phaseId)));
        return mapper.toResoponseDocument(repository.save(taskEntity));
    }

    public DocumentResponse findDocumentById(Long id) {
        logger.info("Finding one Document by id: {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseDocument)
                .orElseThrow(()-> new EntityNotFoundException("Document not found id"));
    }

    @Transactional
    public DocumentResponse updateDocument(@NotNull DocumentUpdateRequest documentDTO, Long id) {
        logger.info("Update one Document by id: {}.", id);
        Document entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Document not found id"));
        mapper.updateEntityDocument(documentDTO, entityDB);
        return mapper.toResoponseDocument(repository.save(entityDB));
    }

    @Transactional
    public void deleteDocument(Long id) {
        logger.info("Deleting one Document by id {}.", id);
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }



}
