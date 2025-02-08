package org.example.jobportal.Service;

import org.example.jobportal.DTO.JobDTO;
import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.User;
import org.example.jobportal.Repository.JobRepository;
import org.example.jobportal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public Optional<JobDTO> getJobById(Long id) {
        return jobRepository.findById(id).map(this::convertToDTO);
    }

    public List<JobDTO> getJobsByEmployer(Long employerId) {
        return jobRepository.findByEmployerId(employerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO saveJob(Job job) {
        Job savedJob = jobRepository.save(job);
        return convertToDTO(savedJob);
    }

    private JobDTO convertToDTO(Job job) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getEmploymentType().name(),
                job.getEmployer().getId(),
                job.getJobType().name(),
                job.getRequirements(),
                job.getStatus().name(),
                job.getSalary()
        );
    }
}
