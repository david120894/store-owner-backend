package com.example.backend_store.store.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.product.category.entity.Category;

import com.example.backend_store.product.inventory.entity.Inventory;
import com.example.backend_store.product.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private Boolean deleted = false;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;
    @OneToMany(mappedBy = "store",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "store",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Inventory> inventories = new HashSet<>();
}
