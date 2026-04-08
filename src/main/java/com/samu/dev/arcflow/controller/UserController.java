package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.dto.user.UserCreateRequest;
import com.samu.dev.arcflow.dto.user.UserResponse;
import com.samu.dev.arcflow.dto.user.UserUpdateRequest;
import com.samu.dev.arcflow.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/office/{officeId}/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserCreateRequest user,
            @PathVariable Long officeId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user, officeId));
    }

    @GetMapping
    public ResponseEntity<UserResponse> find(@RequestParam String name){
        return ResponseEntity.ok(service.findUserByName(name));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<UserResponse> findById( @PathVariable Long id){
        return ResponseEntity.ok(service.findUserById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<UserResponse> update(
            @Valid @RequestBody UserUpdateRequest user,
            @PathVariable Long id){
        return ResponseEntity.ok(service.updateUser(user, id));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
