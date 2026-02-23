package com.example.chatchat.controllers;

import com.example.chatchat.models.User;
import com.example.chatchat.models.dto.auth.RegistrationRequestDto;
import com.example.chatchat.services.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody RegistrationRequestDto registerDto,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.registrationNewUser(registerDto, response));
    }
}
