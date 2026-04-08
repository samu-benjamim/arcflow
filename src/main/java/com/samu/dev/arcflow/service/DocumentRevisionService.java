package com.samu.dev.arcflow.service;


import com.samu.dev.arcflow.dto.document.DocumentCreateRequest;
import com.samu.dev.arcflow.dto.document.DocumentResponse;
import com.samu.dev.arcflow.dto.document.DocumentUpdateRequest;
import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionCreateRequest;
import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionResponse;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Document;
import com.samu.dev.arcflow.model.DocumentRevision;
import com.samu.dev.arcflow.repository.DocumentRepository;
import com.samu.dev.arcflow.repository.DocumentRevisionRepository;
import com.samu.dev.arcflow.repository.ProjectPhaseRepository;
import com.samu.dev.arcflow.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentRevisionService {

    private final Logger logger = LoggerFactory.getLogger(DocumentRevisionService.class.getName());

    private final DocumentService documentervice;

    private final DocumentRevisionRepository repository;


    private final ObjectMapper mapper;

    public DocumentRevisionService(DocumentService documentervice, DocumentRevisionRepository repository, ObjectMapper mapper) {
        this.documentervice = documentervice;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public DocumentRevisionResponse createDocumentRevision(DocumentRevisionCreateRequest documentDTO, Long documentId) {
        logger.info("Create one Document Revision.");
        DocumentRevision taskEntity = mapper.toEntityDocumentRevision(documentDTO);
        taskEntity.setDocument(mapper.toResoponseConvertDocument(documentervice.findDocumentById(documentId)));
        return mapper.toResoponseDocumentRevision(repository.save(taskEntity));
    }

    public DocumentRevisionResponse findDocumentRevisionById(Long id) {
        logger.info("Finding one Document Revision by id: {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseDocumentRevision)
                .orElseThrow(()-> new EntityNotFoundException("Document Revision not found id"));
    }

    @Transactional
    public void deleteDocumentRevision(Long id) {
        logger.info("Deleting one Document Revision by id: {}.", id);
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }
}
