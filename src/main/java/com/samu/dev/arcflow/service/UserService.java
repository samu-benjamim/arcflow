package com.samu.dev.arcflow.service;


import com.samu.dev.arcflow.dto.user.UserCreateRequest;
import com.samu.dev.arcflow.dto.user.UserResponse;
import com.samu.dev.arcflow.dto.user.UserUpdateRequest;
import com.samu.dev.arcflow.exception.BusinessException;
import com.samu.dev.arcflow.mapper.ObjectMapper;
import com.samu.dev.arcflow.model.User;
import com.samu.dev.arcflow.model.types.Role;
import com.samu.dev.arcflow.repository.UserRepository;
import com.samu.dev.arcflow.security.UserDetailsImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;

    private final OfficeService officeService;

    private final ObjectMapper mapper;

    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(UserRepository repository, OfficeService officeService, ObjectMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.officeService = officeService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public UserResponse createUser(UserCreateRequest userDTO, Long officeId) {
        logger.info("Creating user in office {}.", officeId);
        if (repository.existsByEmail(userDTO.email())) {
            throw new BusinessException("Email já cadastrado");
        }
        User userEntity = mapper.toEntityUser(userDTO);
        userEntity.setPasswordHash(passwordEncoder.encode(userDTO.password()));
        userEntity.setOffice(mapper.toResoponseConvertOffice(officeService.findOfficeById(officeId)));
        userEntity.setActive(true);
        return mapper.toResoponseUser(repository.save(userEntity));
    }

    public UserResponse findUserByName(String nameUser) {
        logger.info("Finding user by name: {}.", nameUser);
        return repository.findByName(nameUser)
                .map(mapper::toResoponseUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + nameUser));
    }

    public UserResponse findUserById(Long id) {
        logger.info("Finding user by id {}.", id);
        return repository.findById(id)
                .map(mapper::toResoponseUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
    }

    @Transactional
    public UserResponse updateUser(@NotNull UserUpdateRequest userDTO, Long id) {
        logger.info("Updating user id {}.", id);
        User entityDB = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
        mapper.updateEntityUser(userDTO, entityDB);
        return mapper.toResoponseUser(repository.save(entityDB));
    }

    @Transactional
    public void deleteUser(Long id) {
        logger.info("Deleting user id {}.", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
        repository.delete(user);
    }
    @PreAuthorize("hasRole('DIRETOR')")
    @Transactional
    public UserResponse addMember(UserCreateRequest dto,
                                  UserDetailsImpl currentUser) {
        if (repository.existsByEmail(dto.email())) {
            throw new BusinessException("Email já cadastrado");
        }

        if (dto.role().equals(Role.DIRETOR)) {
            throw new BusinessException("Não é permitido criar outro proprietário");
        }

        User member = new User();
        member.setName(dto.name());
        member.setEmail(dto.email());
        member.setPasswordHash(passwordEncoder.encode(dto.password()));
        member.setRole(dto.role());
        member.setOffice(currentUser.getUser().getOffice());
        member.setActive(true);

        return mapper.toResoponseUser(repository.save(member));
    }

}
