package com.example.backend_store.auth.entity;

import com.example.backend_store.person.entity.Person;
import jakarta.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "users")
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Token> tokens = new HashSet<>();

}
