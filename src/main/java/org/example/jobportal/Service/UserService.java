package org.example.jobportal.Service;

import jakarta.transaction.Transactional;
import org.example.jobportal.DTO.UserDTO;
import org.example.jobportal.Model.User;
import org.example.jobportal.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public UserDTO saveUser(User user) {
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(this::convertToDTO);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name() // Convert ENUM to String
        );
    }
}
