package com.springcrud1.service;

import com.springcrud1.DTO.RegisterRequest;
import com.springcrud1.Security.AuthRequest;
import com.springcrud1.Security.AuthResponse;
import com.springcrud1.Security.JwtUtil;
import com.springcrud1.entity.User;
import com.springcrud1.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    public String register(RegisterRequest request) {

        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());

        // 🔐 encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("EMPLOYEE"); // default role
        user.setVemp_code(request.getVemp_code());
        userRepo.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(AuthRequest request) {
        AuthResponse response = new AuthResponse();
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        Claims claims = Jwts.claims();
        claims.put("Role", user.getRole());
        claims.put("vemp_code", user.getVemp_code());

        String token = jwtUtil.generateToken(user.getUsername(),claims);


        response.setUsername(user.getUsername());
        response.setToken(token);
        response.setLoginTime(LocalDateTime.now());
        response.setMessage("Login Successfully");

        return response;
    }

}
