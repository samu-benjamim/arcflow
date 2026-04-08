package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseCreateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseResponse;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseUpdateRequest;
import com.samu.dev.arcflow.dto.task.TaskCreateRequest;
import com.samu.dev.arcflow.dto.task.TaskResponse;
import com.samu.dev.arcflow.dto.task.TaskUpdateRequest;
import com.samu.dev.arcflow.service.PhaseProjectService;
import com.samu.dev.arcflow.service.TaskService;
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
@RequestMapping("/phases/{phaseId}/tasks ")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody TaskCreateRequest task,
            @PathVariable Long phaseId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTask(task, phaseId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<TaskResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findTaskById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<TaskResponse> update(
            @Valid @RequestBody TaskUpdateRequest task,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateTask(task, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
