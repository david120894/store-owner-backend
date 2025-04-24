package com.example.backend_store.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_store.auth.dto.ApiResponse;
import com.example.backend_store.person.dto.PersonDTO;
import com.example.backend_store.person.service.PersonService;


@RestController
@RequestMapping("/api/person")
public class PersonController{
    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> getPersonById(@PathVariable Long id) {
        PersonDTO person = personService.getPersonById(id);
        return ResponseEntity.ok(
                new ApiResponse<>( "Person retrieved successfully",
                        HttpStatus.OK.value(),
                        person)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> updatePerson(@PathVariable Long id, @RequestBody PersonDTO person) {
        PersonDTO updatedPerson = personService.updatePerson(id, person);
        return ResponseEntity.ok(
                new ApiResponse<>( "Person updated successfully",
                        HttpStatus.OK.value(),
                        updatedPerson)
        );
    }
}