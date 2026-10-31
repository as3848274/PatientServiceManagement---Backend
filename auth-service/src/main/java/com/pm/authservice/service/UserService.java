package com.pm.authservice.service;

import com.pm.authservice.Exceptions.UserNotFoundException;
import com.pm.authservice.dto.RegisterRequestDTO;
import com.pm.authservice.model.User;
import com.pm.authservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo repo , PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    public Optional<User> findByEmail(String email){
        return repo.findByEmail(email);
    }

    public List<User> getall() {
        return repo.findAll();
    }

    public User register(RegisterRequestDTO dto){
        if(repo.findByEmail(dto.getEmail()).isPresent()) throw new RuntimeException("email already exists.");

        User newuser = new User();
        newuser.setEmail(dto.getEmail());
        newuser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newuser.setRole(dto.getRole());

        return repo.save(newuser);
    }
}
