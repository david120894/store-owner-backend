package com.example.backend_store.seller.entity;

import com.example.backend_store.product.product.entity.Product;
import com.example.backend_store.product.purchase.entity.PurchaseDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sellers")
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @OneToMany(mappedBy = "seller",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PurchaseDetail> purchaseDetails = new HashSet<>();

}
