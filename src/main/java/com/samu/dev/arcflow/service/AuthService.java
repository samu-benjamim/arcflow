package com.samu.dev.arcflow.service;

import com.samu.dev.arcflow.dto.auth.AuthResponse;
import com.samu.dev.arcflow.dto.auth.LoginRequest;
import com.samu.dev.arcflow.dto.auth.RegisterRequest;
import com.samu.dev.arcflow.exception.BusinessException;
import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.model.User;
import com.samu.dev.arcflow.model.types.Role;
import com.samu.dev.arcflow.repository.OfficeRepository;
import com.samu.dev.arcflow.repository.UserRepository;
import com.samu.dev.arcflow.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;


    @Transactional
    public AuthResponse register(RegisterRequest dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Email já cadastrado");
        }

        Office office = new Office();
        office.setName(dto.officeName());
        office.setCnpj(dto.cnpj());
        office.setEmail(dto.officeEmail());
        officeRepository.save(office);

        User owner = new User();
        owner.setName(dto.name());
        owner.setEmail(dto.email());
        owner.setPasswordHash(passwordEncoder.encode(dto.password()));
        owner.setRole(Role.DIRETOR);
        owner.setOffice(office);
        owner.setActive(true);
        userRepository.save(owner);

        log.info("Novo escritório registrado: {} | Owner: {}", office.getName(), owner.getEmail());

        String token = jwtService.generateToken(owner);
        return new AuthResponse(token, owner.getName(), owner.getEmail(),
                owner.getRole().name(), office.getId());
    }

    public AuthResponse login(LoginRequest dto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getName(), user.getEmail(),
                user.getRole().name(), user.getOffice().getId());
    }
}
