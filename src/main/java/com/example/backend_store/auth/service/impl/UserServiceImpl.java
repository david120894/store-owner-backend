package com.example.backend_store.auth.service.impl;

import com.example.backend_store.auth.dto.JwtResponseDto;
import com.example.backend_store.auth.dto.LoginDto;
import com.example.backend_store.auth.dto.UserDto;
import com.example.backend_store.auth.dto.UserResponseDTO;
import com.example.backend_store.auth.entity.Role;
import com.example.backend_store.auth.entity.User;
import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.auth.exceptions.JwtAuthenticationException;
import com.example.backend_store.auth.exceptions.NotFoundException;
import com.example.backend_store.auth.repository.RolRepository;
import com.example.backend_store.auth.repository.UserRepository;
import com.example.backend_store.auth.security.JwtGenerator;
import com.example.backend_store.auth.service.UserService;
import com.example.backend_store.person.dto.PersonDTO;
import com.example.backend_store.person.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User register(UserDto user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ConflictException("Username already exists");
        }
        Set<Role> roles = user.getRoles().stream()
                .map(roleName -> rolRepository.findByName(roleName)
                        .orElseThrow(() -> new NotFoundException("Role not found: " + roleName)))
                .collect(Collectors.toSet());


        PersonDTO personDTO = user.getPerson();
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setPhone(personDTO.getPhone());
        person.setAddress(personDTO.getAddress());
        person.setCity(personDTO.getCity());
        person.setDni(personDTO.getDni());

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(roles);
        newUser.setPerson(person);

        return userRepository.save(newUser);   
    }

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            User user = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(user.getPerson().getId());
            personDTO.setFirstName(user.getPerson().getFirstName());
            personDTO.setLastName(user.getPerson().getLastName());
            personDTO.setEmail(user.getPerson().getEmail());
            personDTO.setPhone(user.getPerson().getPhone());
            personDTO.setAddress(user.getPerson().getAddress());
            personDTO.setCity(user.getPerson().getCity());
            personDTO.setDni(user.getPerson().getDni());
            userResponseDTO.setPerson(personDTO);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtGenerator.generateToken(authentication);
            JwtResponseDto jwtResponseDto = new JwtResponseDto();
            jwtResponseDto.setToken(jwt);
            jwtResponseDto.setUser(userResponseDTO);
            return jwtResponseDto;

        } catch (AuthenticationException e) {
            throw new JwtAuthenticationException("Invalid username or password");
        }
    }

    @Override
    public UserDto getLogoutUser(HttpHeaders headers) {
        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found");
        } else {
            return users.stream().map(user -> {
                UserResponseDTO userDto = new UserResponseDTO();
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
                PersonDTO personDto = new PersonDTO();
                personDto.setId(user.getPerson().getId());
                personDto.setFirstName(user.getPerson().getFirstName());
                personDto.setLastName(user.getPerson().getLastName());
                personDto.setEmail(user.getPerson().getEmail());
                personDto.setPhone(user.getPerson().getPhone());
                personDto.setAddress(user.getPerson().getAddress());
                personDto.setCity(user.getPerson().getCity());
                personDto.setDni(user.getPerson().getDni());
                userDto.setPerson(personDto);
                return userDto;
            }).toList();
        }
    }
}
