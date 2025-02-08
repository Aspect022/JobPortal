package org.example.jobportal.Controller;

import org.example.jobportal.DTO.UserDTO;
import org.example.jobportal.Model.User;
import org.example.jobportal.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ GET User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ CREATE a new User
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        UserDTO savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
