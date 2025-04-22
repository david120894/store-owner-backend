package com.example.backend_store.controller;

import com.example.backend_store.entity.Role;
import com.example.backend_store.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestParam String name) {
        Role role = roleService.createRole(name);
        return ResponseEntity.ok(role);
    }
}
