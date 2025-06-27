package com.example.backend_store.auth.dto;

import com.example.backend_store.person.dto.PersonDTO;

import lombok.Data;

import java.util.Set;
@Data

public class UserDto {
    private String username;
    private String password;
    private Set<String> roles;
    private PersonDTO person;
}
