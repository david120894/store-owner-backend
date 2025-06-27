package com.example.backend_store.product.presentations.entity;

import com.example.backend_store.product.inventory.entity.Inventory;
import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.product.purchase.entity.Purchase;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "presentations")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String unit;
    private String unitType;
    private Integer stockQuantity;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inventory> inventories = new HashSet<>();

    @PrePersist
    @PreUpdate
    public void generateName() {
        if (product != null && unit != null && unitType != null) {
            this.name = product.getName() + " " + unit + unitType;
        }
    }
}