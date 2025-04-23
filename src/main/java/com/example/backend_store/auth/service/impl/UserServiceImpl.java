package com.example.backend_store.auth.service.impl;

import com.example.backend_store.auth.dto.JwtResponseDto;
import com.example.backend_store.auth.dto.LoginDto;
import com.example.backend_store.auth.dto.RegisterDto;
import com.example.backend_store.auth.dto.UserDto;
import com.example.backend_store.auth.entity.Role;
import com.example.backend_store.auth.entity.User;
import com.example.backend_store.auth.exceptions.ConflictException;
import com.example.backend_store.auth.exceptions.JwtAuthenticationException;
import com.example.backend_store.auth.exceptions.NotFoundException;
import com.example.backend_store.auth.repository.RolRepository;
import com.example.backend_store.auth.repository.UserRepository;
import com.example.backend_store.auth.security.JwtGenerator;
import com.example.backend_store.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void register(RegisterDto user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ConflictException("Username already exists");
        } else {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));

            Role role = rolRepository.findByName("ADMIN")
                    .orElseThrow(() -> new NotFoundException("Role not found"));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            newUser.setRoles(roles);
            userRepository.save(newUser);

            UserDto userDto = new UserDto();
            userDto.setUsername(newUser.getUsername());
            userDto.setPassword(newUser.getPassword());
            userDto.setRoles(newUser.getRoles());

        }
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
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtGenerator.generateToken(authentication);
            JwtResponseDto jwtResponseDto = new JwtResponseDto();
            jwtResponseDto.setToken(jwt);
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
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found");
        } else {
            return users.stream().map(user -> {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setPassword(user.getPassword());
                userDto.setRoles(user.getRoles());
                return userDto;
            }).toList();
        }
    }
}
