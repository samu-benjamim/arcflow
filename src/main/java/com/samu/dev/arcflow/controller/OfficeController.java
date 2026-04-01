package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.service.OfficeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @PostMapping
    public Office create(@RequestBody Office office){
        return service.createOffice(office);
    }

    @GetMapping
    public Office find(@RequestParam String name){
        return service.findOfficeByName(name);
    }

    @PatchMapping ("/{id}")
    public Office update(@RequestBody Office office, @PathVariable Long id){
        return service.updateOffice(office, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteOffice(id);
    }
}
