package com.example.chatchat.services.auth;

import com.example.chatchat.enums.Role;
import com.example.chatchat.models.User;
import com.example.chatchat.models.dto.auth.AuthResponseDto;
import com.example.chatchat.models.dto.auth.LoginRequestDto;
import com.example.chatchat.models.dto.auth.RegistrationRequestDto;
import com.example.chatchat.repositories.UserRepository;
import com.example.chatchat.services.token.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    // Registration new user
    @Override
    public User registrationNewUser(RegistrationRequestDto registerDto, HttpServletResponse response) {

        User newUser = User.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .firstName(registerDto.getFirstName())
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(newUser);

        return newUser;
    }

    // Login user
    @Override
    public AuthResponseDto loginUser(LoginRequestDto loginDto, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true); // true в проде (https)
        refreshCookie.setPath("/api/auth/refresh");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60); // 7 дней
        refreshCookie.setAttribute("SameSite", "Strict"); // если Servlet 6+

        response.addCookie(refreshCookie);

        return new AuthResponseDto(accessToken);
    }
}
