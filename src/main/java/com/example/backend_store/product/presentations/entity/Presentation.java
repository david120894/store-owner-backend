package com.example.backend_store.product.presentations.entity;

import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.product.purchase.entity.Purchase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

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
}
