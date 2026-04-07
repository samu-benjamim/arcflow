package com.samu.dev.arcflow.controller;


import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalCreateRequest;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalResponse;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalUpdateRequest;
import com.samu.dev.arcflow.service.ClientApprovalService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/office/{officeId}/client/{clientId}/project/{projectId}/phase/{phaseId}/document/{documentId}/clientapproval")
public class ClientApprovalController {

    private final ClientApprovalService service;

    public ClientApprovalController(ClientApprovalService service) {
        this.service = service;
    }

    @PostMapping
    public ClientApprovalResponse create(
            @RequestBody ClientApprovalCreateRequest task,
            @PathVariable Long phaseId){
        return service.createClientApproval(task, phaseId);
    }

    @GetMapping ("/{id}")
    public ClientApprovalResponse findById( @PathVariable Long id){
        return service.findClientApprovalById(id);
    }

    @PatchMapping ("/{id}")
    public ClientApprovalResponse update(@RequestBody ClientApprovalUpdateRequest clientApproval, @PathVariable Long id){
        return service.updateClientApproval(clientApproval, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteClientApproval(id);
    }
}
