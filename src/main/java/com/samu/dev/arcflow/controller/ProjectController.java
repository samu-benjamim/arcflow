package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.project.ProjectCreateRequest;
import com.samu.dev.arcflow.dto.project.ProjectSummaryResponse;
import com.samu.dev.arcflow.dto.project.ProjectUpdateRequest;
import com.samu.dev.arcflow.service.ProjectService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offices/{officeId}/clients/{clientId}/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectSummaryResponse> create(
            @Valid @RequestBody ProjectCreateRequest project,
            @PathVariable Long officeId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProject(project, officeId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProjectSummaryResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findProjectById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<ProjectSummaryResponse> update(
            @Valid @RequestBody ProjectUpdateRequest project,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateProject(project, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
