package org.example.jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDTO {
    private Long id;
    private Long jobId;
    private Long employeeId;
    private EmployeeDTO employeeProfile; // Employee details included in application
    private String status; // APPLIED, ACCEPTED, REJECTED
}

