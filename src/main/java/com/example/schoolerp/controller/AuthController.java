package com.example.schoolerp.controller;

import com.example.schoolerp.dto.LoginRequest;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.repository.UserRepository;
import com.example.schoolerp.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String > login(@Valid @RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword());
        authenticationManager.authenticate(token);
        User user = userRepository.findByEmail(request.getEmail());
        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(jwt);
    }
}
