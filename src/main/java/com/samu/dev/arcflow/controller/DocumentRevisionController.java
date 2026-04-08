package com.samu.dev.arcflow.controller;


import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionCreateRequest;
import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionResponse;
import com.samu.dev.arcflow.service.DocumentRevisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/{documentId}/revisions")
public class DocumentRevisionController {

    private final DocumentRevisionService service;

    public DocumentRevisionController(DocumentRevisionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DocumentRevisionResponse> create(
            @Valid @RequestBody DocumentRevisionCreateRequest revision,
            @PathVariable Long documentId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createDocumentRevision(revision, documentId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DocumentRevisionResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findDocumentRevisionById(id));
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteDocumentRevision(id);
        return ResponseEntity.noContent().build();
    }
}
