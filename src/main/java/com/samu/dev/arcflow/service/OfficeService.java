package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.office.OfficeCreateRequest;
import com.samu.dev.arcflow.dto.office.OfficeResponse;
import com.samu.dev.arcflow.dto.office.OfficeUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.repository.OfficeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    private final Logger logger = LoggerFactory.getLogger(OfficeService.class.getName());

    private final OfficeRepository repository;

    private final ObjectMapper mapper;

    public OfficeService(OfficeRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public OfficeResponse createOffice(OfficeCreateRequest officeDTO) {
        logger.info("Create one Office.");
        Office officeEntity = mapper.toEntityOffice(officeDTO);
        return mapper.toResoponseOffice(repository.save(officeEntity));
    }

    public OfficeResponse findOfficeByName(String nameOffice) {
        logger.info("Finding one Office by name.");
       return repository.findByName(nameOffice)
                .map(mapper::toResoponseOffice)
                .orElseThrow(()-> new EntityNotFoundException("Office not found with name: " + nameOffice));
    }

    public OfficeResponse findOfficeById(Long id) {
        logger.info("Finding one Office by id.");
        return repository.findById(id)
                .map(mapper::toResoponseOffice)
                .orElseThrow(()-> new EntityNotFoundException("Office not found id"));
    }

    public OfficeResponse updateOffice(@NotNull OfficeUpdateRequest officeDTO, Long id) {
        logger.info("Update one Office.");
        Office entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Office not found id"));
        mapper.updateEntityOffice(officeDTO, entityDB);
        return mapper.toResoponseOffice(repository.save(entityDB));
    }

    public void deleteOffice(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }
}
