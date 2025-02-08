package org.example.jobportal.Model.UserTypes;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobportal.Model.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private String department;
}