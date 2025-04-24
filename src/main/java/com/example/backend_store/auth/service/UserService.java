package com.example.backend_store.auth.service;

import com.example.backend_store.auth.dto.JwtResponseDto;
import com.example.backend_store.auth.dto.LoginDto;
import com.example.backend_store.auth.dto.UserDto;
import com.example.backend_store.auth.dto.UserResponseDTO;
import com.example.backend_store.auth.entity.User;

import java.util.List;

import org.springframework.http.HttpHeaders;


public interface UserService {
    public User register(UserDto registerDto);

    public JwtResponseDto login(LoginDto loginDto);

    public List<UserResponseDTO> getAllUsers();

    UserDto getLogoutUser(HttpHeaders headers);
}
