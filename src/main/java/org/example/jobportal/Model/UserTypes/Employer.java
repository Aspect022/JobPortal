package org.example.jobportal.Model.UserTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.Job;
import org.example.jobportal.Model.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("EMPLOYER")
@JsonIgnoreProperties(ignoreUnknown = true) // Allows extra fields like username, email, password
public class Employer extends User {
    private String companyName;
    private String companyLocation;
    private String industryType;

    @OneToMany(mappedBy = "employer")
 //   @JsonManagedReference // Prevents infinite recursion
    @JsonIgnore
    private List<Job> jobPostings; // Jobs posted by this employer

}
