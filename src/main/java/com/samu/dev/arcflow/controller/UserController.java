package com.samu.dev.arcflow.controller;

import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.model.User;
import com.samu.dev.arcflow.service.OfficeService;
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
@RequestMapping("/office/{officeId}/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user, @PathVariable Long officeId){
        return service.createUser(officeId, user);
    }

    @GetMapping
    public User find(@RequestParam String name){
        return service.findUserByName(name);
    }

    @PatchMapping ("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id){
        return service.updateUser(user, id);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteUser(id);
    }
}
