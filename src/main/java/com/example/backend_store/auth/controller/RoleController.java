package com.example.backend_store.auth.controller;

import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.auth.dto.RolDto;
import com.example.backend_store.auth.entity.Role;
import com.example.backend_store.auth.service.RoleService;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<List<Role>>> getRole() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(
            new ApiResponse<>( "Roles retrieved successfully",
                        HttpStatus.OK.value(),
                        roles)
        );
    }

    @PostMapping("/roles")
    public ResponseEntity<ApiResponse<RolDto>> createRole(@RequestBody RolDto role) {
        RolDto rol = roleService.createRole(role);
        return ResponseEntity.ok(
            new ApiResponse<>( "Role created successfully",
                        HttpStatus.CREATED.value(),
                        rol)
        );
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<RolDto>> updateRole(@PathVariable Long id, @RequestBody @Valid RolDto role) {
        RolDto rol = roleService.updateRole(id, role);
        return ResponseEntity.ok(
                new ApiResponse<>( "Role updated successfully",
                        HttpStatus.OK.value(),
                        rol)
        );
    }
}
