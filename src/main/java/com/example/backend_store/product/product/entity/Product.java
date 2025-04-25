package com.example.backend_store.product.product.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.product.presentations.entity.Presentation;
import com.example.backend_store.product.store.entity.Store;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @OneToMany(mappedBy = "product", 
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Presentation>  presentations= new HashSet<>();
    
}
