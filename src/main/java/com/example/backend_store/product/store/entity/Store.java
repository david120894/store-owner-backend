package com.example.backend_store.product.store.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.product.product.entity.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    @OneToMany(mappedBy = "store",
            cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product> products = new HashSet<>();
}
