package com.example.backend_store.dto;

import com.example.backend_store.entity.Role;
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
