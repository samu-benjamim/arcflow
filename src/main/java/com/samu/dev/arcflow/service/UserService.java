package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.office.OfficeCreateRequest;
import com.samu.dev.arcflow.dto.office.OfficeResponse;
import com.samu.dev.arcflow.dto.office.OfficeUpdateRequest;
import com.samu.dev.arcflow.dto.user.UserCreateRequest;
import com.samu.dev.arcflow.dto.user.UserResponse;
import com.samu.dev.arcflow.dto.user.UserUpdateRequest;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.Office;
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

    private final ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(UserRepository repository, OfficeService officeService, OfficeService officeService1, ObjectMapper mapper) {
        this.repository = repository;
        this.officeService = officeService1;
        this.mapper = mapper;
    }

    public UserResponse createUser(UserCreateRequest userDTO, Long officeId) {
        logger.info("Create one User.");
        User userEntity = mapper.toEntityUser(userDTO);
        userEntity.setOffice(mapper.toResoponseConvertOffice(officeService.findOfficeById(officeId)));
        return mapper.toResoponseUser(repository.save(userEntity));
    }

    public UserResponse findUserByName(String nameUser) {
        logger.info("Finding one User by name.");
        return repository.findByName(nameUser)
                .map(mapper::toResoponseUser)
                .orElseThrow(()-> new EntityNotFoundException("User not found with name: " + nameUser));
    }

    public UserResponse findUserById(Long id) {
        logger.info("Finding one User by id.");
        return repository.findById(id)
                .map(mapper::toResoponseUser)
                .orElseThrow(()-> new EntityNotFoundException("User not found id"));
    }

    public UserResponse updateUser(@NotNull UserUpdateRequest userDTO, Long id) {
        logger.info("Update one User.");
        User entityDB = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found id"));
        mapper.updateEntityUser(userDTO, entityDB);
        return mapper.toResoponseUser(repository.save(entityDB));
    }

    public void deleteUser(Long id) {
        logger.info("Deleting one Office.");
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No records found for this ID")));
    }


}
