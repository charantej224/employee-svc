package com.employee.service.web.rest;

import com.employee.service.config.CRUD_OPERATIONS;
import com.employee.service.repository.CustomEmployeeRepository;
import com.employee.service.request.domain.EmployeeRequest;
import com.employee.service.request.domain.GenericMessage;
import com.employee.service.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeResourceTest {

    @Mock
    private CustomEmployeeRepository mockEmployeeRepository;
    @Mock
    private EmployeeService mockEmployeeService;

    @InjectMocks
    private EmployeeResource employeeResourceUnderTest;

    EmployeeRequest employeeRequest;

    @Before
    public void setUp() {
        employeeRequest = new EmployeeRequest();
        initMocks(this);
        GenericMessage<String> genericMessage = new GenericMessage<>("OK", "OK");
        when(mockEmployeeService.getEmployee(1L)).thenReturn(new ResponseEntity<>(genericMessage, HttpStatus.OK));
        when(mockEmployeeService.deleteEmployee(1L)).thenReturn(new ResponseEntity<>(genericMessage, HttpStatus.OK));
        when(mockEmployeeService.saveEmployee(employeeRequest, CRUD_OPERATIONS.CREATE)).thenReturn((new ResponseEntity<>(genericMessage, HttpStatus.OK)));
        when(mockEmployeeService.saveEmployee(employeeRequest, CRUD_OPERATIONS.UPDATE)).thenReturn((new ResponseEntity<>(genericMessage, HttpStatus.OK)));

    }

    @Test
    public void testCreateEmployee() {
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.createEmployee(employeeRequest);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", result.getBody().getMessageBody());
        assertEquals("OK", result.getBody().getMessage());


    }

    @Test
    public void testUpdateEmployee() {
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.updateEmployee(employeeRequest);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", result.getBody().getMessageBody());
        assertEquals("OK", result.getBody().getMessage());
    }

    @Test
    public void testGetEmployee() {
        final Long id = 1L;
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.getEmployee(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals("OK", ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testDeleteEmployee() {
        final ResponseEntity<GenericMessage> result = employeeResourceUnderTest.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals("OK", ((GenericMessage<String>) result.getBody()).getMessageBody());
    }
}
