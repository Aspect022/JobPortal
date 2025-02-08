package org.example.jobportal.Model;

import jakarta.persistence.*;
import lombok.*;
import org.example.jobportal.Model.enums.UserRole;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED) // Used for Job Seeker & Employer inheritance
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    private String provider;


    @Enumerated(EnumType.STRING)
    @Column(name = "user_role") // Rename column to avoid duplication
    private UserRole role; // EMPLOYEE, EMPLOYER, ADMIN
}
