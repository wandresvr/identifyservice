package com.flowlite.identifyservice.application.services;
import com.flowlite.identifyservice.domain.entities.User;
import com.flowlite.identifyservice.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.flowlite.identifyservice.application.ports.PasswordEncoder;
import com.flowlite.identifyservice.application.ports.TokenProvider;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Aquí validarías password real con BCrypt (ejemplo simplificado)
        if (!passwordEncoder.matches(rawPassword, user.getPassword().getValue())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // Generar token
        return tokenProvider.generateToken(user);
    }
}
