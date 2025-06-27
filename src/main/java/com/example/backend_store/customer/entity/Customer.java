package com.example.backend_store.customer.entity;

import java.util.HashSet;
import java.util.Set;

import com.example.backend_store.product.purchase.entity.Purchase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String phone;
    private String address;
    private Integer dni;
    @OneToMany(mappedBy = "customer" ,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Purchase> purchases = new HashSet<>();
}
