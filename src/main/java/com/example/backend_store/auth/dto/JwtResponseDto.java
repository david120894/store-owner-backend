package com.example.backend_store.auth.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String token;
    private UserResponseDTO user;
}
