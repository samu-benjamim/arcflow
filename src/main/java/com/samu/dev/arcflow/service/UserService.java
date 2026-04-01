package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.model.User;
import com.samu.dev.arcflow.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    private final OfficeService officeService;

    private final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(UserRepository repository, OfficeService officeService) {
        this.repository = repository;
        this.officeService = officeService;
    }

    public User createUser(Long officeId, @NotNull User user){
        logger.info("Create one User.");

        User entity = new User();
        entity.setOffice(officeService.findOfficeById(officeId));
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());

        return repository.save(entity);
    }

    public User findUserByName(String nameUser){
        logger.info("Finding one User By Name.");
        return repository.findByName(nameUser).orElseThrow(()-> new EntityNotFoundException("User not found with name: " + nameUser));
    }

    public User findUserById(Long id){
        logger.info("Finding one User By Id.");
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("user not found id."));
    }

    public User updateUser(@NotNull User user, Long userId){
        logger.info("Update one User.");
        User entity = findUserById(userId);
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());

        return repository.save(entity);
    }

    public void deleteUser(Long id){
        logger.info("Deleting one User.");
        repository.delete(repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Office not found id.")));
    }

}
