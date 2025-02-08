package org.example.jobportal.Repository;

import org.example.jobportal.Model.UserTypes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
