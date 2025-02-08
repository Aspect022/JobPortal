package org.example.jobportal.Model.UserTypes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.User;
import org.example.jobportal.Model.enums.EmploymentStatus;
import org.example.jobportal.Model.enums.EmploymentType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("EMPLOYEE")
@JsonIgnoreProperties(ignoreUnknown = true) // Allows extra fields like username, email, password
public class Employee extends User {
    private int age;
    private String gender;
    private String country;
    private String fullName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;  // Remote / On-site / Hybrid

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private String resumeUrl;

    @ManyToOne
    @JoinColumn(name = "employer_id")
//    @JsonBackReference // Prevents infinite recursion
    private Employer currentEmployer;
}