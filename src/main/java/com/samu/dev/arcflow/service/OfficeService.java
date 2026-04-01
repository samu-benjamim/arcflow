package com.samu.dev.arcflow.service;

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

    public OfficeService(OfficeRepository repository) {
        this.repository = repository;
    }

    public Office createOffice(Office office) {
        logger.info("Create one Office.");
        return repository.save(office);
    }

    public Office findOfficeByName(String nameOffice) {
        logger.info("Finding one Office by name.");
        return repository.findByName(nameOffice).orElseThrow(()-> new EntityNotFoundException("Office not found with name: " + nameOffice));
    }

    public Office findOfficeById(Long id) {
        logger.info("Finding one Office by id.");
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Office not found id"));
    }

    public Office updateOffice(@NotNull Office office, Long id) {
        logger.info("Update one Office.");
        Office entityDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No records found for this ID"));
        entityDB.setName(office.getName());
        entityDB.setEmail(office.getEmail());
        return repository.save(entityDB);
    }

    public void deleteOffice(Long id) {
        logger.info("Deleting one Office.");
        Office entityDB = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No records found for this ID"));;
        repository.delete(entityDB);
    }
}
