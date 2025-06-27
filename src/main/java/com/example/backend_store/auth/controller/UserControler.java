package com.example.backend_store.auth.controller;

import java.util.List;

import com.example.backend_store.auth.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_store.auth.dto.UserResponseDTO;
import com.example.backend_store.auth.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserControler {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
//        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "List User successfuly",
                        HttpStatus.OK.value(),
                        userService.getAllUsers()
                )
        );
    }
    
}
