package com.fed.mivivienda.auth;

import com.fed.mivivienda.entity.User;
import com.fed.mivivienda.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    // ✅ Registro de usuario con contraseña encriptada
    public User register(String email, String rawPassword, String role) {
        User u = new User(email, passwordEncoder.encode(rawPassword), role);
        return userRepository.save(u);
    }

    // ✅ Adaptado para Spring Security: devuelve UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Convertimos nuestra entidad User en un objeto UserDetails de Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRole()) // usa el campo role de tu entidad
                .build();
    }
}
