package com.example.backend_store.person.service;

import com.example.backend_store.auth.dto.UserResponseDTO;
import com.example.backend_store.person.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getPersonAll();
    PersonDTO createPerson(PersonDTO userDto);
    PersonDTO updatePerson(Long idPerson,PersonDTO userDto);
    PersonDTO getPersonById(Long id);
    void deletePerson(Long id);
    UserResponseDTO getPersonByUsername(String username);
}
