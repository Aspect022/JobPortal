package org.example.jobportal.Repository;

import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployerId(Long employerid) ; // Get jobs posted by an employer
}
