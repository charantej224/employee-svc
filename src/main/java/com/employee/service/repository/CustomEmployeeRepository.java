package com.employee.service.repository;

import com.employee.service.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomEmployeeRepository extends EmployeeRepository {
    Optional<Employee> getByEmail(String email);

}
