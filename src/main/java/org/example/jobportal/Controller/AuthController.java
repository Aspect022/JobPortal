package org.example.jobportal.Controller;

import org.example.jobportal.Model.User;
import org.example.jobportal.Repository.UserRepository;
import org.example.jobportal.Security.JwtUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "User already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Hash password
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials!");
        }
    }

        @GetMapping("/user")
        public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
            return principal.getAttributes(); // Returns user's profile info
        }

}
