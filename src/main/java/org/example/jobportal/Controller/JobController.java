package org.example.jobportal.Controller;

import org.example.jobportal.DTO.JobDTO;
import org.example.jobportal.Model.Job;
import org.example.jobportal.Service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobDTO>> getJobsByEmployer(@PathVariable Long employerId) {
        List<JobDTO> jobs = jobService.getJobsByEmployer(employerId);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody Job job) {
        JobDTO savedJob = jobService.saveJob(job);
        return ResponseEntity.ok(savedJob);
    }
}
