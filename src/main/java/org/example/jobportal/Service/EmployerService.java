package org.example.jobportal.Service;


import org.example.jobportal.DTO.EmployerDTO;
import org.example.jobportal.Model.UserTypes.Employer;
import org.example.jobportal.Repository.EmployerRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Optional<EmployerDTO> getEmployerById(Long id) {
        return employerRepository.findById(id).map(this::convertToDTO);
    }

    public EmployerDTO saveEmployer(Employer employer) {
        Employer savedEmployer = employerRepository.save(employer);
        return convertToDTO(savedEmployer);
    }

    private EmployerDTO convertToDTO(Employer employer) {
        return new EmployerDTO(
                employer.getCompanyName(),
                employer.getCompanyLocation(),
                employer.getIndustryType()
        );
    }

}
