package com.example.management.service;

import common.dto.UserDto;
import common.mapper.UserMapper;
import common.model.Role;
import common.model.User;
import common.repository.RoleRepository;
import common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto dto) {
        Role role = null;
        if (dto.getRole() != null && dto.getRole().getId() != null) {
            role = roleRepository.findById(dto.getRole().getId())
                    .orElseThrow(() -> new NoSuchElementException("Role not found"));
        }

        System.out.println("[UserService] Creating user with role: " + (role != null ? role.getRoleName() : "null"));
        User user = userMapper.toEntity(dto);
        user.setRole(role);
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        System.out.println("[UserService] Saving user: " + user.getUsername() + ", role: " + (role != null ? role.getRoleName() : "null"));
        User saved = userRepository.save(user);
        System.out.println("[UserService] User saved with ID: " + saved.getId());
        return userMapper.toDto(saved);
    }

    public List<UserDto> getAllUsers() {
        System.out.println("[UserService] Getting all users");
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        System.out.println("[UserService] Getting user by ID: " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Integer id, UserDto dto) {
        System.out.println("[UserService] Updating user ID: " + id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        System.out.println("[UserService] Existing user role: " + (existingUser.getRole() != null ? existingUser.getRole().getRoleName() : "null"));
        Role role = roleRepository.findById(dto.getRole().getId())
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
        System.out.println("[UserService] New role for update: " + role.getRoleName());

        if (dto.getUsername() != null) {
            existingUser.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            existingUser.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }

        existingUser.setRole(role);

        User updated = userRepository.save(existingUser);
        return userMapper.toDto(updated);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserDto validateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Invalid username or password"));
        System.out.println("[UserService] validateUser found user: " + user.getUsername() + ", role: " + (user.getRole() != null ? user.getRole().getRoleName() : "null"));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw(new IllegalArgumentException("Invalid username or password"));
        }
        return userMapper.toDto(user);
    }

}
