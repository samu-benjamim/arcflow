package com.samu.dev.arcflow.controller;


import com.samu.dev.arcflow.dto.document.DocumentCreateRequest;
import com.samu.dev.arcflow.dto.document.DocumentResponse;
import com.samu.dev.arcflow.dto.document.DocumentUpdateRequest;
import com.samu.dev.arcflow.service.DocumentService;
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
    public DocumentResponse create(
            @RequestBody DocumentCreateRequest task,
            @PathVariable Long phaseId){
        return service.createDocument(task, phaseId);
    }

    @GetMapping ("/{id}")
    public DocumentResponse findById( @PathVariable Long id){
        return service.findDocumentById(id);
    }

    @PatchMapping ("/{id}")
    public DocumentResponse update(@RequestBody DocumentUpdateRequest task, @PathVariable Long id){
        return service.updateDocument(task, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteDocument(id);
    }
}
