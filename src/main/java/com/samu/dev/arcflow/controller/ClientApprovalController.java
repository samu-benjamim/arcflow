package com.samu.dev.arcflow.controller;


import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalCreateRequest;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalResponse;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalUpdateRequest;
import com.samu.dev.arcflow.service.ClientApprovalService;
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
@RequestMapping("/documents/{documentId}/approvals")
public class ClientApprovalController {

    private final ClientApprovalService service;

    public ClientApprovalController(ClientApprovalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientApprovalResponse> create(
            @Valid @RequestBody ClientApprovalCreateRequest clientApproval,
            @PathVariable Long documentId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createClientApproval(clientApproval, documentId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ClientApprovalResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findClientApprovalById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<ClientApprovalResponse> update(
            @Valid @RequestBody ClientApprovalUpdateRequest clientApproval,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateClientApproval(clientApproval, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteClientApproval(id);
        return ResponseEntity.noContent().build();
    }
}
