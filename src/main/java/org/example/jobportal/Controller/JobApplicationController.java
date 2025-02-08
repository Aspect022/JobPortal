package org.example.jobportal.Controller;


import org.example.jobportal.DTO.JobApplicationDTO;
import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.JobApplication;
import org.example.jobportal.Model.UserTypes.Employee;
import org.example.jobportal.Model.enums.ApplicationStatus;
import org.example.jobportal.Repository.EmployeeRepository;
import org.example.jobportal.Repository.JobApplicationRepository;
import org.example.jobportal.Repository.JobRepository;
import org.example.jobportal.Service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @Autowired
    private EmployeeRepository employeeRepository; // Ensure you have this

    @Autowired
    private JobRepository jobRepository; // Ensure you have this

    @Autowired
    private JobApplicationRepository jobApplicationRepository; // Ensure you have this

    @PostMapping("/apply")
    public JobApplication applyForJob(@RequestBody JobApplication jobApplication) {
        if (jobApplication.getEmployee() == null || jobApplication.getJob() == null) {
            throw new RuntimeException("Employee or Job data is missing in the request");
        }

        Employee employee = employeeRepository.findById(jobApplication.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Job job = jobRepository.findById(jobApplication.getJob().getId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        jobApplication.setEmployee(employee);
        jobApplication.setJob(job);

        return jobApplicationRepository.save(jobApplication);
    }



    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationDTO>> getApplicationsByJob(@PathVariable Long jobId) {
        List<JobApplicationDTO> applications = jobApplicationService.getApplicationsByJob(jobId);
        return ResponseEntity.ok(applications);
    }

}
