package com.example.backend_store.product.product.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.product.category.entity.Category;
import com.example.backend_store.product.inventory.entity.Inventory;
import com.example.backend_store.product.presentations.entity.Presentation;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Boolean deleted=false;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;
    @OneToMany(mappedBy = "product", 
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Presentation>  presentations= new HashSet<>();
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category productCategory;
    @OneToMany(mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Inventory> inventories = new HashSet<>();
}
