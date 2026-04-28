package com.samu.dev.arcflow.security;

import com.samu.dev.arcflow.model.User;
import com.samu.dev.arcflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @NotNull
    @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuário não encontrado: " + email));

        if (!user.isActive()) {
            throw new DisabledException("Usuário inativo");
        }

        return new UserDetailsImpl(user);
    }
}