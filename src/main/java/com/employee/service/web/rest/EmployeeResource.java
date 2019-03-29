package com.employee.service.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.employee.service.config.CRUD_OPERATIONS;
import com.employee.service.repository.CustomEmployeeRepository;
import com.employee.service.request.domain.EmployeeRequest;
import com.employee.service.request.domain.GenericMessage;
import com.employee.service.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

/**
 * REST controller for managing Employee.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    private CustomEmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;


    /**
     * POST  /employees : Create a new employee.
     *
     * @param employeeRequest the employee to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employee, or with status 400 (Bad Request) if the employee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employees")
    @Timed
    public ResponseEntity<GenericMessage> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.saveEmployee(employeeRequest, CRUD_OPERATIONS.CREATE);
    }

    /**
     * PUT  /employees : Updates an existing employee.
     *
     * @param employeeRequest the employee to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employee,
     * or with status 400 (Bad Request) if the employee is not valid,
     * or with status 500 (Internal Server Error) if the employee couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employees")
    @Timed
    public ResponseEntity<GenericMessage> updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.saveEmployee(employeeRequest, CRUD_OPERATIONS.UPDATE);
    }

    /**
     * GET  /employees/:id : get the "id" employee.
     *
     * @param id the id of the employee to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employee, or with status 404 (Not Found)
     */
    @GetMapping("/employees/{id}")
    @Timed
    public ResponseEntity<GenericMessage> getEmployee(@PathVariable Long id) {
        log.debug("REST request to get Employee : {}", id);
        return employeeService.getEmployee(id);
    }

    /**
     * DELETE  /employees/:id : delete the "id" employee.
     *
     * @param id the id of the employee to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employees/{id}")
    @Timed
    public ResponseEntity<GenericMessage> deleteEmployee(@PathVariable Long id) {
        log.debug("REST request to delete Employee : {}", id);
        return employeeService.deleteEmployee(id);
    }
}
