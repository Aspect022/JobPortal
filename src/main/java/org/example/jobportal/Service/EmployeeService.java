package org.example.jobportal.Service;

import org.example.jobportal.DTO.EmployeeDTO;
import org.example.jobportal.Model.UserTypes.Employee;
import org.example.jobportal.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToDTO);
    }

    public EmployeeDTO saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getFullName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getResumeUrl(),
                employee.getEmploymentType().name()
        );
    }
}
