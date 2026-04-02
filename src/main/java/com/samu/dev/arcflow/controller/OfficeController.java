package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.office.OfficeCreateRequest;
import com.samu.dev.arcflow.dto.office.OfficeResponse;
import com.samu.dev.arcflow.dto.office.OfficeUpdateRequest;
import com.samu.dev.arcflow.service.OfficeService;
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
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @PostMapping
    public OfficeResponse create(@RequestBody OfficeCreateRequest office){
        return service.createOffice(office);
    }

    @GetMapping
    public OfficeResponse find(@RequestParam String name){
        return service.findOfficeByName(name);
    }

    @GetMapping ("/{id}")
    public OfficeResponse findById(@PathVariable Long id){
        return service.findOfficeById(id);
    }

    @PatchMapping ("/{id}")
    public OfficeResponse update(@RequestBody OfficeUpdateRequest office, @PathVariable Long id){
        return service.updateOffice(office, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteOffice(id);
    }
}
