package com.example.backend_store.person.dto;

import lombok.Data;

@Data
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String phone;
    private String address;
    private String city;
}
