package com.employee.service.repository;

import com.employee.service.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomDepartmentRepository extends DepartmentRepository {
    Optional<Department> getByName(String name);
}
