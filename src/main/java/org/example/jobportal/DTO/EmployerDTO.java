package org.example.jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDTO {
    private String companyName;
    private String companyLocation;
    private String industryType;
}
