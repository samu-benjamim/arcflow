package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.client.ClientCreateRequest;
import com.samu.dev.arcflow.dto.client.ClientResponse;
import com.samu.dev.arcflow.dto.client.ClientUpdateRequest;
import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.service.ClientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/office/{officeId}/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ClientResponse create(@RequestBody ClientCreateRequest client, @PathVariable Long officeId ){
        return service.createClient(client, officeId);
    }

    @GetMapping
    public ClientResponse find(@RequestParam String name){
        return service.findClientByName(name);
    }

    @GetMapping ("/{id}")
    public ClientResponse find(@PathVariable Long id){
        return service.findClientById(id);
    }

    @PatchMapping("/{id}")
    public ClientResponse update(@RequestBody ClientUpdateRequest client , @PathVariable Long id){
        return service.updateClient(client , id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteClient(id);
    }
}
