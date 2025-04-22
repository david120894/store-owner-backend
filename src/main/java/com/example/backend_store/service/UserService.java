package com.example.backend_store.service;

import com.example.backend_store.dto.JwtResponseDto;
import com.example.backend_store.dto.LoginDto;
import com.example.backend_store.dto.RegisterDto;
import com.example.backend_store.dto.UserDto;
import com.example.backend_store.entity.User;
import org.springframework.http.HttpHeaders;


public interface UserService {
    public UserDto register(RegisterDto registerDto);

    public JwtResponseDto login(LoginDto loginDto);

    UserDto getLoguedUser(HttpHeaders headers);
}
