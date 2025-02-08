package org.example.jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.enums.ApplicationStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDTO {
    private Long applicationId; // ID of the job application
    private Long jobId;
    private Long employeeId;
    private ApplicationStatus status; // ACCEPTED / REJECTED
    private String message; // Optional message (e.g., "Congrats, you're hired!")
}
