package com.example.backend_store.auth.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.person.dto.PersonDTO;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private Set<String> roles = new HashSet<>();;
    private PersonDTO person;
}
