package org.example.jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.enums.EmploymentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String resumeUrl;
    private String employmentType; // Remote, On-site, Hybrid
}

