package com.hari.netflix.controller;

import com.hari.netflix.auth.AuthenticationResponse;
import com.hari.netflix.dto.AdminRegistrationRequestDto;
import com.hari.netflix.dto.UserLoginRequestDto;
import com.hari.netflix.dto.UserRegistrationRequestDto;
import com.hari.netflix.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/v1/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserRegistrationRequestDto request) {
        return authenticationService.register(request);
    }

    @PostMapping("/v1/auth/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequestDto request) {
        return authenticationService.login(request);
    }


    @PostMapping("/v1/auth/admin/register")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@Valid @RequestBody AdminRegistrationRequestDto request) {
        return authenticationService.registerAdmin(request);
    }
}
