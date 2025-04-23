package com.example.backend_store.auth.dto;

import com.example.backend_store.auth.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
