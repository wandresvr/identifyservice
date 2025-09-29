package com.flowlite.identifyservice.infrastructure.controllers;

import com.flowlite.identifyservice.application.services.LoginUserService;
import com.flowlite.identifyservice.application.services.RegisterUserService;
import com.flowlite.identifyservice.domain.entities.User;
import com.flowlite.identifyservice.infrastructure.dtos.LoginRequest;
import com.flowlite.identifyservice.infrastructure.dtos.RegisterRequest;
import com.flowlite.identifyservice.infrastructure.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserService registerUserService;
    private final LoginUserService loginUserService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        User user = registerUserService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        String jwt = tokenProvider.generateToken(user);
        return ResponseEntity.ok(Map.of("access_token", jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String jwt = loginUserService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("access_token", jwt));
    }
}
