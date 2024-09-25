package com.bearhug.presentation.controller.auth;

import com.bearhug.presentation.dto.auth.AuthCreateUserRequest;
import com.bearhug.presentation.dto.auth.AuthLoginRequest;
import com.bearhug.presentation.dto.auth.AuthResponse;
import com.bearhug.service.implementation.user.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthenticationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsService.createUser(userRequest));
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest loginRequest) {
        return ResponseEntity.ok(userDetailsService.loginUser(loginRequest));
    }
}
