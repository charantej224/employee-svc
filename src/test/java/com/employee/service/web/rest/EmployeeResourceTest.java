package com.employee.service.web.rest;

import com.employee.service.domain.Employee;
import com.employee.service.repository.CustomEmployeeRepository;
import com.employee.service.request.domain.EmployeeRequest;
import com.employee.service.request.domain.GenericMessage;
import com.employee.service.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeResourceTest {

    @Mock
    private CustomEmployeeRepository mockEmployeeRepository;
    @Mock
    private EmployeeService mockEmployeeService;

    @InjectMocks
    private EmployeeResource employeeResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        // Setup
        final EmployeeRequest employeeRequest = null;
        final ResponseEntity<GenericMessage> expectedResult = null;

        // Run the test
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.createEmployee(employeeRequest);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateEmployee() {
        // Setup
        final EmployeeRequest employeeRequest = null;
        final ResponseEntity<GenericMessage> expectedResult = null;

        // Run the test
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.updateEmployee(employeeRequest);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetEmployee() {
        // Setup
        final Long id = 0L;
        final ResponseEntity<GenericMessage> expectedResult = null;

        // Run the test
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.getEmployee(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteEmployee() {
        // Setup
        final Long id = 0L;
        final ResponseEntity<GenericMessage> expectedResult = null;

        // Run the test
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.deleteEmployee(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
