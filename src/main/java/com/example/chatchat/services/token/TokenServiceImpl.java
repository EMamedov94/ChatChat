package com.example.chatchat.services.token;

import com.example.chatchat.config.jwt.JwtService;
import com.example.chatchat.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtService jwtService;

    @Override
    public String generateAccessToken(User user) {
        return jwtService.generateToken(
                user,
                "access",
                15 * 60 * 1000 // 15 минут
        );
    }

    @Override
    public String generateRefreshToken(User user) {
        return jwtService.generateToken(
                user,
                "refresh",
                7 * 24 * 60 * 60 * 1000 // 7 дней
        );
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public String extractUsername(String token) {
        return "";
    }
}
