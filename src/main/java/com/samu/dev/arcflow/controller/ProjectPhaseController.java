package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.project.ProjectCreateRequest;
import com.samu.dev.arcflow.dto.project.ProjectSummaryResponse;
import com.samu.dev.arcflow.dto.project.ProjectUpdateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseCreateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseResponse;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseUpdateRequest;
import com.samu.dev.arcflow.model.ProjectPhase;
import com.samu.dev.arcflow.service.PhaseProjectService;
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
@RequestMapping("/projects/{projectId}/phases")
public class ProjectPhaseController {

    private final PhaseProjectService service;

    public ProjectPhaseController(PhaseProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectPhaseResponse> create(
            @Valid @RequestBody ProjectPhaseCreateRequest projectphase,
            @PathVariable Long projectId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProjectPhase(projectphase, projectId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProjectPhaseResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findProjectPhaseById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<ProjectPhaseResponse> update(
            @Valid @RequestBody ProjectPhaseUpdateRequest project,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateProject(project, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
