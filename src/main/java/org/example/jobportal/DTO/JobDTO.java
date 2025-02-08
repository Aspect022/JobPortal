package org.example.jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.enums.EmploymentType;
import org.example.jobportal.Model.enums.JobStatus;
import org.example.jobportal.Model.enums.JobType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String employmentType; // Remote, On-site, Hybrid
    private Long employerId; // To track which employer posted the job
    private String jobType;
    private String requirements;
    private String jobstatus;
    private double salary;


}


