package com.example.backend_store.services;

import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;

    public String generateToken(String username) {
        return buildToken(username, expiration);
    }
}
