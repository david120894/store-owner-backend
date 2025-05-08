package com.example.backend_store.auth.controller;

import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.auth.dto.JwtResponseDto;
import com.example.backend_store.auth.dto.LoginDto;
import com.example.backend_store.auth.dto.UserDto;
import com.example.backend_store.auth.dto.UserResponseDTO;
import com.example.backend_store.auth.entity.Role;
import com.example.backend_store.auth.entity.User;
import com.example.backend_store.auth.security.JwtGenerator;
import com.example.backend_store.auth.service.UserService;
import com.example.backend_store.person.dto.PersonDTO;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponseDto>> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Login successful",
                HttpStatus.OK.value(),
                userService.login(loginDto)
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@RequestBody UserDto registerDto) {
        User user = userService.register(registerDto);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        PersonDTO personDTO = new PersonDTO(
                user.getPerson().getId(),
                user.getPerson().getFirstName(),
                user.getPerson().getLastName(),
                user.getPerson().getEmail(),
                user.getPerson().getDni(),
                user.getPerson().getPhone(),
                user.getPerson().getAddress(),
                user.getPerson().getCity()
        );
//        personDTO.setId(user.getPerson().getId());
//        personDTO.setFirstName(user.getPerson().getFirstName());
//        personDTO.setLastName(user.getPerson().getLastName());
//        personDTO.setEmail(user.getPerson().getEmail());
//        personDTO.setPhone(user.getPerson().getPhone());
//        personDTO.setAddress(user.getPerson().getAddress());
//        personDTO.setCity(user.getPerson().getCity());
        userResponseDTO.setPerson(personDTO);

        userResponseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
);
        return ResponseEntity.ok(new ApiResponse<>(
                "User registered successfully",
                HttpStatus.OK.value(),
                userResponseDTO
        ));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToke(Authentication authentication) {

        String token = jwtGenerator.refreshToken(authentication);

        JwtResponseDto jwtRefresh = new JwtResponseDto();
        jwtRefresh.setToken(token);
        return new ResponseEntity<JwtResponseDto>(jwtRefresh, HttpStatus.OK);
    }
}
