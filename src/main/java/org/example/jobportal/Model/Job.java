package org.example.jobportal.Model;

import jakarta.persistence.*;
import lombok.*;
import org.example.jobportal.Model.UserTypes.Employee;
import org.example.jobportal.Model.UserTypes.Employer;
import org.example.jobportal.Model.enums.EmploymentType;
import org.example.jobportal.Model.enums.JobStatus;
import org.example.jobportal.Model.enums.JobType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private JobType jobType; // FULL_TIME, PART_TIME, CONTRACT

    private String location;
    private double salary;

    @Enumerated(EnumType.STRING)
    private JobStatus status; // OPEN, CLOSED, FILLED

    @ManyToOne
    @JoinColumn(name = "employer_id")  // The employer who posted the job
    private Employer employer;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private String requirements;

    @ManyToMany
    @JoinTable(
            name = "job_applications",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> applicants; // Employees who applied for this job


}
