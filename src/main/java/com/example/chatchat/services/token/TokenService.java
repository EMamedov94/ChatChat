package com.example.chatchat.services.token;

import com.example.chatchat.models.User;

public interface TokenService {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    boolean validateToken(String token);
    String extractUsername(String token);
}
