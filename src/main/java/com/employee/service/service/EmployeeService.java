package com.employee.service.service;

import com.employee.service.config.ApplicationProperties;
import com.employee.service.config.CRUD_OPERATIONS;
import com.employee.service.domain.Department;
import com.employee.service.domain.Employee;
import com.employee.service.repository.CustomDepartmentRepository;
import com.employee.service.repository.CustomEmployeeRepository;
import com.employee.service.request.domain.EmployeeRequest;
import com.employee.service.request.domain.GenericMessage;
import com.employee.service.web.rest.util.EmployeeServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.employee.service.config.Constants.*;

@Service
public class EmployeeService {

    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    CustomEmployeeRepository employeeRepository;

    @Autowired
    CustomDepartmentRepository departmentRepository;

    private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public ResponseEntity<GenericMessage> saveEmployee(EmployeeRequest employeeRequest, CRUD_OPERATIONS operations) {
        GenericMessage<String> genericMessage;
        Employee employeeEntity = new Employee();
        try {

            if (employeeRequest.getEmail() == null) {
                genericMessage = new GenericMessage(MESSAGE_101, applicationProperties.getMessages().get(MESSAGE_101));
                return ResponseEntity.badRequest().headers(httpHeaders).body(genericMessage);
            }
            Optional<Department> department = departmentRepository.getByName(employeeRequest.getDepartmentName());
            if (!department.isPresent()) {
                genericMessage = new GenericMessage(MESSAGE_102, applicationProperties.getMessages().get(MESSAGE_102));
                return ResponseEntity.badRequest().headers(httpHeaders).body(genericMessage);
            }
            Optional<Employee> employee = employeeRepository.getByEmail(employeeRequest.getEmail());
            if (employee.isPresent() && CRUD_OPERATIONS.CREATE.equals(operations)) {
                genericMessage = new GenericMessage(MESSAGE_103, applicationProperties.getMessages().get(MESSAGE_103));
                return ResponseEntity.badRequest().headers(httpHeaders).body(genericMessage);

            } else if (!employee.isPresent() && CRUD_OPERATIONS.UPDATE.equals(operations)) {
                genericMessage = new GenericMessage(MESSAGE_104, applicationProperties.getMessages().get(MESSAGE_104));
                return ResponseEntity.badRequest().headers(httpHeaders).body(genericMessage);
            } else if (employee.isPresent() && CRUD_OPERATIONS.UPDATE.equals(operations)) {
                employeeEntity = employee.get();
            }

            employeeEntity.setEmail(employeeRequest.getEmail());
            employeeEntity.setDepartment(department.get());
            employeeEntity.setFullName(employeeRequest.getFullName());
            employeeEntity.setBirthDay(EmployeeServiceUtils.getLocalDate(employeeRequest.getBirthDay()));
            employeeEntity = employeeRepository.save(employeeEntity);
            genericMessage = new GenericMessage(MESSAGE_200, applicationProperties.getMessages().get(MESSAGE_200) + employeeEntity.getId());
            return ResponseEntity.ok().headers(httpHeaders).body(genericMessage);
        } catch (RuntimeException runtimeException) {
            logger.error("Exception Occurred", runtimeException);
            genericMessage = new GenericMessage<>(runtimeException.getClass().getName(), runtimeException.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericMessage);
        }
    }

}