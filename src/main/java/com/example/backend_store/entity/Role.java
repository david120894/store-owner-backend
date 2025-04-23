package com.example.backend_store.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Table(name = "roles")
@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
}
