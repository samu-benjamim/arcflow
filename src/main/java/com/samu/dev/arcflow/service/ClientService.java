package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.model.User;
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

    public ClientService(ClientRepository repository, OfficeService officeService) {
        this.repository = repository;
        this.officeService = officeService;
    }

    public Client createClient(@NotNull Client client, Long officeId){
        logger.info("Create one Client.");
        Client entity = new Client();
        entity.setOffice(officeService.findOfficeById(officeId));
        entity.setName(client.getName());
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        entity.setCpfCnpj(client.getCpfCnpj());
        entity.setAddress(client.getAddress());

        return repository.save(entity);
    }

    public Client findClientByName(String nameClient){
        logger.info("Finding one Client By Name.");
        return repository.findByName(nameClient).orElseThrow(()-> new EntityNotFoundException("Client not found with name: " + nameClient));
    }

    public Client findClientById(Long id){
        logger.info("Finding one Client By Id.");
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Client not found id."));
    }

    public Client updateClient(@NotNull Client client, Long ClientId){
        logger.info("Update one User.");
        Client entity = findClientById(ClientId);
        entity.setName(client.getName());
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        entity.setCpfCnpj(client.getCpfCnpj());
        entity.setAddress(client.getAddress());

        return repository.save(entity);
    }

    public void deleteClient(Long id){
        logger.info("Deleting one Client.");
        repository.delete(repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Client not found id.")));
    }

}
