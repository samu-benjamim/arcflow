package com.samu.dev.arcflow.controller;


import com.samu.dev.arcflow.dto.document.DocumentCreateRequest;
import com.samu.dev.arcflow.dto.document.DocumentResponse;
import com.samu.dev.arcflow.dto.document.DocumentUpdateRequest;
import com.samu.dev.arcflow.service.DocumentService;
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
@RequestMapping("/phases/{phaseId}/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DocumentResponse> create(
            @Valid @RequestBody DocumentCreateRequest task,
            @PathVariable Long phaseId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createDocument(task, phaseId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DocumentResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findDocumentById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<DocumentResponse> update(
            @Valid @RequestBody DocumentUpdateRequest task,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateDocument(task, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
