package com.example.backend_store.service;

import com.example.backend_store.dto.JwtResponseDto;
import com.example.backend_store.dto.LoginDto;
import com.example.backend_store.dto.RegisterDto;
import com.example.backend_store.dto.UserDto;

import java.util.List;

import org.springframework.http.HttpHeaders;


public interface UserService {
    public void register(RegisterDto registerDto);

    public JwtResponseDto login(LoginDto loginDto);

    public List<UserDto> getAllUsers();

    UserDto getLogoutUser(HttpHeaders headers);
}
