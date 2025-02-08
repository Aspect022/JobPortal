package org.example.jobportal.Controller;

import org.example.jobportal.DTO.EmployerDTO;
import org.example.jobportal.Model.UserTypes.Employer;
import org.example.jobportal.Service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerDTO> getEmployerById(@PathVariable Long id) {
        Optional<EmployerDTO> employer = employerService.getEmployerById(id);
        return employer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<EmployerDTO> createEmployer(@RequestBody Employer employer) {
        EmployerDTO savedEmployer = employerService.saveEmployer(employer);
        return ResponseEntity.ok(savedEmployer);
    }
}
