package org.example.jobportal.Repository;

import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.JobApplication;
import org.example.jobportal.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByEmployeeId(Long employeeId); // Fetch all applications by an employee
    List<JobApplication> findByJobId(Long jobId); // Fetch all applications for a job

}
