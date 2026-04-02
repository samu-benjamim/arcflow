package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.project.ProjectCreateRequest;
import com.samu.dev.arcflow.dto.project.ProjectSummaryResponse;
import com.samu.dev.arcflow.dto.project.ProjectUpdateRequest;
import com.samu.dev.arcflow.dto.user.UserCreateRequest;
import com.samu.dev.arcflow.dto.user.UserResponse;
import com.samu.dev.arcflow.dto.user.UserUpdateRequest;
import com.samu.dev.arcflow.service.ProjectService;
import com.samu.dev.arcflow.service.UserService;
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
@RequestMapping("/office/{officeId}/client/{clientId}/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectSummaryResponse create(
            @RequestBody ProjectCreateRequest project,
            @PathVariable Long officeId,
            @PathVariable Long clientId){
        return service.createProject(project, officeId, clientId);
    }

    @GetMapping ("/{id}")
    public ProjectSummaryResponse findById( @PathVariable Long id){
        return service.findProjectById(id);
    }

    @PatchMapping ("/{id}")
    public ProjectSummaryResponse update(@RequestBody ProjectUpdateRequest project, @PathVariable Long id){
        return service.updateProject(project, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteProject(id);
    }
}
