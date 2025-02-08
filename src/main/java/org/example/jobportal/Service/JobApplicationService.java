package org.example.jobportal.Service;


import org.example.jobportal.DTO.EmployeeDTO;
import org.example.jobportal.DTO.JobApplicationDTO;
import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.JobApplication;
import org.example.jobportal.Model.UserTypes.Employee;
import org.example.jobportal.Model.enums.ApplicationStatus;
import org.example.jobportal.Repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public Optional<JobApplicationDTO> getApplicationById(Long id) {
        return jobApplicationRepository.findById(id).map(this::convertToDTO);
    }

    public List<JobApplicationDTO> getApplicationsByEmployee(Long employeeId) {
        return jobApplicationRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JobApplicationDTO> getApplicationsByJob(Long jobId) {
        return jobApplicationRepository.findByJobId(jobId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobApplicationDTO applyForJob(JobApplicationDTO applicationDTO) {
        JobApplication application = convertToEntity(applicationDTO);
        application.setStatus(ApplicationStatus.APPLIED);
        return convertToDTO(jobApplicationRepository.save(application));
    }
    private JobApplication convertToEntity(JobApplicationDTO dto) {
        JobApplication jobApplication = new JobApplication();

        Job job = new Job(); // Create a Job object and set the ID
        job.setId(dto.getJobId());
        jobApplication.setJob(job);

        Employee employee = new Employee(); // Create an Employee object and set the ID
        employee.setId(dto.getEmployeeId());
        jobApplication.setEmployee(employee);

        jobApplication.setStatus(ApplicationStatus.valueOf(String.valueOf(dto.getStatus()))); // Convert String to Enum

        return jobApplication;
    }


    public JobApplicationDTO updateApplicationStatus(Long id, ApplicationStatus status) {
        Optional<JobApplication> applicationOpt = jobApplicationRepository.findById(id);
        if (applicationOpt.isPresent()) {
            JobApplication application = applicationOpt.get();
            application.setStatus(status);
            return convertToDTO(jobApplicationRepository.save(application));
        }
        return null;
    }

    private JobApplicationDTO convertToDTO(JobApplication application) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
                application.getEmployee().getId(),
                application.getEmployee().getFullName(),
                application.getEmployee().getEmail(),
                application.getEmployee().getPhoneNumber(),
                application.getEmployee().getResumeUrl(),
                application.getEmployee().getEmploymentType().name()
        );
        return new JobApplicationDTO(
                application.getId(),
                application.getJob().getId(),
                application.getEmployee().getId(),
                employeeDTO, // Fixed: Properly converted employee details
                application.getStatus().name()
        );
    }

}

