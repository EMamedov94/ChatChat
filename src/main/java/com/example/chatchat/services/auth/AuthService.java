package com.example.chatchat.services.auth;

import com.example.chatchat.models.User;
import com.example.chatchat.models.dto.auth.AuthResponseDto;
import com.example.chatchat.models.dto.auth.LoginRequestDto;
import com.example.chatchat.models.dto.auth.RegistrationRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    User registrationNewUser(RegistrationRequestDto registerDto, HttpServletResponse response);
    AuthResponseDto loginUser(LoginRequestDto loginDto, HttpServletResponse response);
}
