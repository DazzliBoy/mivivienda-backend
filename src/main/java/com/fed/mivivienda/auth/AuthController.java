package com.fed.mivivienda.auth;

import com.fed.mivivienda.dto.LoginRequest;
import com.fed.mivivienda.dto.RegisterRequest;
import com.fed.mivivienda.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        User u = authService.register(req.getEmail(), req.getPassword(), req.getRole());
        return ResponseEntity.ok("Usuario registrado con id: " + u.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );

            if (auth.isAuthenticated()) {
                // Aquí podrías devolver un JWT o token si lo implementas más adelante
                return ResponseEntity.ok("Login correcto");
            } else {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
