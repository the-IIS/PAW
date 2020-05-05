package com.paw.trello.controller;

import com.paw.trello.dto.LoginRequest;
import com.paw.trello.service.AuthService;
import com.paw.trello.service.AuthenticationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthenticationResponse logIn(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
