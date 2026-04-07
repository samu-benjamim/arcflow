package com.samu.dev.arcflow.service;



import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalCreateRequest;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalResponse;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalUpdateRequest;
import com.samu.dev.arcflow.dto.office.OfficeResponse;
import com.samu.dev.arcflow.dto.office.OfficeUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.ClientApproval;
import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.repository.ClientApprovalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientApprovalService {

    private final Logger logger = LoggerFactory.getLogger(ClientApprovalService.class.getName());

    private final DocumentService documentService;

    private final ClientApprovalRepository repository;

    private final ObjectMapper mapper;

    public ClientApprovalService(DocumentService documentService, ClientApprovalRepository repository, ObjectMapper mapper) {
        this.documentService = documentService;
        this.repository = repository;
        this.mapper = mapper;
    }


    public ClientApprovalResponse createClientApproval(ClientApprovalCreateRequest documentDTO, Long documentId) {
        logger.info("Create one ClientApproval Revision.");
        ClientApproval taskEntity = mapper.toEntityClientApproval(documentDTO);
        taskEntity.setDocument(mapper.toResoponseConvertDocument(documentService.findDocumentById(documentId)));
        return mapper.toResoponseClientApproval(repository.save(taskEntity));
    }

    public ClientApprovalResponse findClientApprovalById(Long id) {
        logger.info("Finding one ProjectPhase by id.");
        return repository.findById(id)
                .map(mapper::toResoponseClientApproval)
                .orElseThrow(()-> new EntityNotFoundException("ClientApproval Revision not found id"));
    }

    public ClientApprovalResponse updateClientApproval(@NotNull ClientApprovalUpdateRequest clientApprovalDTO, Long id) {
        logger.info("Update one Office.");
        ClientApproval entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("client Approval not found id"));
        mapper.updateEntityClientApproval(clientApprovalDTO, entityDB);
        return mapper.toResoponseClientApproval(repository.save(entityDB));
    }

    public void deleteClientApproval(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }
}
