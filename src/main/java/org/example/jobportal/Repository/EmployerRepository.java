package org.example.jobportal.Repository;

import org.example.jobportal.Model.UserTypes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

}