package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.timeentry.TimeEntryCreateRequest;
import com.samu.dev.arcflow.dto.timeentry.TimeEntryResponse;
import com.samu.dev.arcflow.service.TimeEntryService;
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
@RequestMapping("/tasks/{taskId}/time-entries")
public class TimeEntryController {

    private final TimeEntryService service;

    public TimeEntryController(TimeEntryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TimeEntryResponse> create(
            @Valid @RequestBody TimeEntryCreateRequest time,
            @PathVariable Long taskId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTimeEntry(time, taskId));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<TimeEntryResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findTimeEntryById(id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Valid> delete(@PathVariable Long id){
        service.deleteTimeEntry(id);
        return ResponseEntity.noContent().build();
    }
}
