package com.samu.dev.arcflow.controller;


import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionCreateRequest;
import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionResponse;
import com.samu.dev.arcflow.service.DocumentRevisionService;
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
    public DocumentRevisionResponse create(
            @RequestBody DocumentRevisionCreateRequest task,
            @PathVariable Long phaseId){
        return service.createDocumentRevision(task, phaseId);
    }

    @GetMapping ("/{id}")
    public DocumentRevisionResponse findById( @PathVariable Long id){
        return service.findDocumentRevisionById(id);
    }


    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteDocumentRevision(id);
    }
}
