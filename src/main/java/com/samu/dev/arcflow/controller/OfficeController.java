package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.office.OfficeCreateRequest;
import com.samu.dev.arcflow.dto.office.OfficeResponse;
import com.samu.dev.arcflow.dto.office.OfficeUpdateRequest;
import com.samu.dev.arcflow.service.OfficeService;
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
@RequestMapping("/office")
public class OfficeController {

    private final OfficeService service;

    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OfficeResponse> create(
            @Valid @RequestBody OfficeCreateRequest office){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOffice(office));
    }

    @GetMapping
    public ResponseEntity<OfficeResponse> find(@RequestParam String name){
        return ResponseEntity.ok(service.findOfficeByName(name));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<OfficeResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findOfficeById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<OfficeResponse> update(
            @Valid @RequestBody OfficeUpdateRequest office,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateOffice(office, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }
}
