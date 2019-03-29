package com.employee.service.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.employee.service.domain.Department;
import com.employee.service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * REST controller for managing Department.
 */
@RestController
@RequestMapping("/api")
public class DepartmentResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private static final String ENTITY_NAME = "employeeServiceDepartment";

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private HttpHeaders httpHeaders;

    /**
     * POST  /departments : Create a new department.
     *
     * @param department the department to create
     * @return the ResponseEntity with status 201 (Created) and with body the new department, or with status 400 (Bad Request) if the department has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/departments")
    @Timed
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) throws URISyntaxException {
        log.debug("REST request to save Department : {}", department);
        Department result = departmentRepository.save(department);
        return ResponseEntity.ok().headers(httpHeaders).body(result);
    }
}
