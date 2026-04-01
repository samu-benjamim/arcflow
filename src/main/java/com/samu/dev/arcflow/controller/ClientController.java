package com.samu.dev.arcflow.controller;

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
    public Client create(@RequestBody Client client,  @PathVariable Long officeId ){
        return service.createClient(client, officeId);
    }

    @GetMapping
    public Client find(@RequestParam String name){
        return service.findClientByName(name);
    }

    @PatchMapping("/{id}")
    public Client update(@RequestBody Client client , @PathVariable Long id){
        return service.updateClient(client , id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteClient(id);
    }
}
