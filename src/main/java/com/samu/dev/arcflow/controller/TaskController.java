package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseCreateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseResponse;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseUpdateRequest;
import com.samu.dev.arcflow.dto.task.TaskCreateRequest;
import com.samu.dev.arcflow.dto.task.TaskResponse;
import com.samu.dev.arcflow.dto.task.TaskUpdateRequest;
import com.samu.dev.arcflow.service.PhaseProjectService;
import com.samu.dev.arcflow.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/office/{officeId}/client/{clientId}/project/{projectId}/phase/{phaseId}/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public TaskResponse create(
            @RequestBody TaskCreateRequest task,
            @PathVariable Long phaseId){
        return service.createTask(task, phaseId);
    }

    @GetMapping ("/{id}")
    public TaskResponse findById( @PathVariable Long id){
        return service.findTaskById(id);
    }

    @PatchMapping ("/{id}")
    public TaskResponse update(@RequestBody TaskUpdateRequest task, @PathVariable Long id){
        return service.updateTask(task, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteTask(id);
    }
}
