package com.employee.service.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.employee.service.domain.Department;
import com.employee.service.repository.DepartmentRepository;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

    /**
     * PUT  /departments : Updates an existing department.
     *
     * @param department the department to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated department,
     * or with status 400 (Bad Request) if the department is not valid,
     * or with status 500 (Internal Server Error) if the department couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/departments")
    @Timed
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) throws URISyntaxException {
        log.debug("REST request to update Department : {}", department);
        Department result = departmentRepository.save(department);
        return ResponseEntity.ok().headers(httpHeaders).body(result);
    }

    /**
     * GET  /departments : get all the departments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of departments in body
     */
    @GetMapping("/departments")
    @Timed
    public List<Department> getAllDepartments() {
        log.debug("REST request to get all Departments");
        return departmentRepository.findAll();
    }

    /**
     * GET  /departments/:id : get the "id" department.
     *
     * @param id the id of the department to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the department, or with status 404 (Not Found)
     */
    @GetMapping("/departments/{id}")
    @Timed
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        log.debug("REST request to get Department : {}", id);
        Optional<Department> department = departmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(department);
    }

    /**
     * DELETE  /departments/:id : delete the "id" department.
     *
     * @param id the id of the department to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/departments/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        log.debug("REST request to delete Department : {}", id);
        departmentRepository.deleteById(id);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }
}
