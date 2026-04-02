package com.samu.dev.arcflow.service;


import com.samu.dev.arcflow.dto.client.ClientCreateRequest;
import com.samu.dev.arcflow.dto.client.ClientResponse;
import com.samu.dev.arcflow.dto.client.ClientUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientService.class.getName());


    private final ClientRepository repository;
    private final OfficeService officeService;
    private final ObjectMapper mapper;

    public ClientService(ClientRepository repository, OfficeService officeService, ObjectMapper mapper) {
        this.repository = repository;
        this.officeService = officeService;
        this.mapper = mapper;
    }

    public ClientResponse createClient(ClientCreateRequest clientDTO, Long officeId) {
        logger.info("Create one Client.");
        Client clientEntity = mapper.toEntityClient(clientDTO);
        clientEntity.setOffice(mapper.toResoponseConvertOffice(officeService.findOfficeById(officeId)));
        return mapper.toResoponseClient(repository.save(clientEntity));
    }

    public ClientResponse findClientByName(String nameClient) {
        logger.info("Finding one Client by name.");
        return repository.findByName(nameClient)
                .map(mapper::toResoponseClient)
                .orElseThrow(()-> new EntityNotFoundException("Client not found with name: " + nameClient));
    }

    public ClientResponse findClientById(Long id) {
        logger.info("Finding one Client by id.");
        return repository.findById(id)
                .map(mapper::toResoponseClient)
                .orElseThrow(()-> new EntityNotFoundException("Client not found id"));
    }

    public ClientResponse updateClient(@NotNull ClientUpdateRequest clientDTO, Long id) {
        logger.info("Update one Client.");
        Client entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Client not found id"));
        mapper.updateEntityClient(clientDTO, entityDB);
        return mapper.toResoponseClient(repository.save(entityDB));
    }

    public void deleteClient(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }


}
