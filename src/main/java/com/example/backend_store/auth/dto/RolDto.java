package com.example.backend_store.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RolDto {
    @NotNull(message = "El id no puede estar vacío")
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
}
