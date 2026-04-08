package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.client.ClientCreateRequest;
import com.samu.dev.arcflow.dto.client.ClientResponse;
import com.samu.dev.arcflow.dto.client.ClientUpdateRequest;
import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClientResponse> create(
            @Valid @RequestBody ClientCreateRequest client,
            @PathVariable Long officeId ){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createClient(client, officeId));
    }

    @GetMapping
    public ResponseEntity<ClientResponse> find(@RequestParam String name){
        return ResponseEntity.ok(service.findClientByName(name));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ClientResponse> find(@PathVariable Long id){
        return ResponseEntity.ok(service.findClientById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponse> update(
            @Valid @RequestBody ClientUpdateRequest client ,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateClient(client , id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
