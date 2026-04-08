package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.modification.ModificationCreateRequest;
import com.samu.dev.arcflow.dto.modification.ModificationResponse;
import com.samu.dev.arcflow.dto.modification.ModificationUpdateRequest;
import com.samu.dev.arcflow.service.ModificationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/modifications")
public class ModificationController {

    private final ModificationService service;

    public ModificationController(ModificationService service) {
        this.service = service;
    }

    @PostMapping
    public ModificationResponse create(
            @RequestBody ModificationCreateRequest modification,
            @PathVariable Long phaseId){
        return service.createModification(modification, phaseId);
    }

    @GetMapping ("/{id}")
    public ModificationResponse findById( @PathVariable Long id){
        return service.findModificationById(id);
    }

    @PatchMapping ("/{id}")
    public ModificationResponse update(@RequestBody ModificationUpdateRequest modification, @PathVariable Long id){
        return service.updateModification(modification, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteModification(id);
    }
}
