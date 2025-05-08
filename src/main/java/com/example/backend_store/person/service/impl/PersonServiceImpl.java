package com.example.backend_store.person.service.impl;

import com.example.backend_store.auth.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_store.auth.dto.UserResponseDTO;
import com.example.backend_store.auth.exceptions.BadRequestException;
import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.person.dto.PersonDTO;
import com.example.backend_store.person.entity.Person;
import com.example.backend_store.person.repository.PersonRepository;
import com.example.backend_store.person.service.PersonService;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDTO> getPersonAll() {
        List<Person> persons = personRepository.findAll();

        return persons.stream().map(person -> new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress(),
                person.getCity(),
                person.getDni()
        )).toList();
    }

    @Override
    public PersonDTO createPerson(PersonDTO userDto) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePerson'");

    }

    @Override
    public PersonDTO updatePerson(Long idPerson,PersonDTO userDto) {
        Person person = personRepository.findById(idPerson)
        .orElseThrow(() -> new BadRequestException("Person not found"));
        person.setFirstName(userDto.getFirstName());
        person.setLastName(userDto.getLastName());
        person.setEmail(userDto.getEmail());
        person.setPhone(userDto.getPhone());
        person.setAddress(userDto.getAddress());
        person.setCity(userDto.getCity());
        person.setDni(userDto.getDni());

        Person updatePerson = personRepository.save(person);

        return new PersonDTO(
                updatePerson.getId(),
                updatePerson.getFirstName(),
                updatePerson.getLastName(),
                updatePerson.getEmail(),
                updatePerson.getPhone(),
                updatePerson.getAddress(),
                updatePerson.getCity(),
                updatePerson.getDni()
        );
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Person not found"));
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress(),
                person.getCity(),
                person.getDni()
        );
    }

    @Override
    public void deletePerson(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePerson'");
    }

    @Override
    public UserResponseDTO getPersonByUsername(String username) {

        // Person person = personRepository.findByUsername(username)
        //         .orElseThrow(() -> new BadRequestException("Person not found"));
        // if (username.isEmpty()) {
        //     throw new ConflictException("Person not found");
        // }
        // UserResponseDTO userResponseDTO = new UserResponseDTO();
        // return userResponseDTO;

        throw new UnsupportedOperationException("Unimplemented method 'deletePerson'");


    }

}
