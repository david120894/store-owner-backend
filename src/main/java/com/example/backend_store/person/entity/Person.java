package com.example.backend_store.person.entity;

import com.example.backend_store.auth.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String phone;
    private String address;
    private String city;
    @OneToOne(mappedBy="person")
    private User user;
}
