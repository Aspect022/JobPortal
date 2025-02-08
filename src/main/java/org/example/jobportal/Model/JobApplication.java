package org.example.jobportal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.DTO.EmployeeDTO;
import org.example.jobportal.Model.UserTypes.Employee;
import org.example.jobportal.Model.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; // APPLIED, ACCEPTED, REJECTED

    private LocalDateTime appliedAt;
    private LocalDateTime decisionAt; // When accepted/rejected

}

