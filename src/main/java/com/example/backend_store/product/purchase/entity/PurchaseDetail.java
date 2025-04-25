package com.example.backend_store.product.purchase.entity;

import com.example.backend_store.product.presentations.entity.Presentation;

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
@Table(name = "purchase_details")
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private Double subtotal;
    private Double discount;
    @ManyToOne()
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    @ManyToOne()
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;


}
