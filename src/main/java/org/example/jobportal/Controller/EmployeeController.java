package org.example.jobportal.Controller;

import org.example.jobportal.DTO.EmployeeDTO;
import org.example.jobportal.Model.UserTypes.Employee;
import org.example.jobportal.Service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }
}
