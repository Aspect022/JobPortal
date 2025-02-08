package org.example.jobportal.Service;

import org.example.jobportal.Model.User;
import org.example.jobportal.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Inject the UserRepository via constructor
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is used by Spring Security during authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Create a Spring Security User object with the user's credentials and roles
        return org.springframework.security.core.userdetails.User.withUsername(appUser.getUsername())
                .password(appUser.getPassword()) // password should be hashed
                .authorities(appUser.getRole().name()) // Convert your role to a simple authority
                .build();
    }
}
