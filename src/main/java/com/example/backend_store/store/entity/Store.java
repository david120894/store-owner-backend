package com.example.backend_store.store.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.product.category.entity.Category;

import com.example.backend_store.product.inventory.entity.Inventory;
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
    private String description;
    private String address;
    @OneToMany(mappedBy = "store",
    cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "store",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Inventory> inventories = new HashSet<>();

}
