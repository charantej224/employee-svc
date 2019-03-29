package com.employee.service.service;

import com.employee.service.config.ApplicationProperties;
import com.employee.service.config.CRUD_OPERATIONS;
import com.employee.service.domain.Department;
import com.employee.service.domain.Employee;
import com.employee.service.domain.EventMessage;
import com.employee.service.repository.CustomDepartmentRepository;
import com.employee.service.repository.CustomEmployeeRepository;
import com.employee.service.request.domain.EmployeeRequest;
import com.employee.service.request.domain.GenericMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private HttpHeaders mockHttpHeaders;
    @Mock
    private ApplicationProperties mockApplicationProperties;
    @Mock
    private CustomEmployeeRepository mockEmployeeRepository;
    @Mock
    private CustomDepartmentRepository mockDepartmentRepository;
    @Mock
    private EventMessageService mockEventMessageService;

    @InjectMocks
    private EmployeeService employeeServiceUnderTest;

    Employee employee;

    @Before
    public void setUp() {
        initMocks(this);
        employee = new Employee();
        employee.setFullName("TestName");
        employee.setBirthDay(LocalDate.now());
        employee.setEmail("test@email.com");
        Map<String, String> messageMap = new HashMap<String, String>();
        messageMap.put("100", "Success");
        messageMap.put("101", "Email Not Found.");
        messageMap.put("102", "No such department Associated.");
        messageMap.put("103", "Employee already existing in the database, bad request");
        messageMap.put("104", "Employee doesn't exist to update the record.");
        messageMap.put("105", "No such employee in the records.");
        messageMap.put("106", "Record Deleted Successfully.");
        messageMap.put("200", "Identifier");
        when(mockEmployeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(mockEmployeeRepository.getByEmail("test@email.com")).thenReturn(Optional.of(employee));
        when(mockApplicationProperties.getMessages()).thenReturn(messageMap);
        doNothing().when(mockEmployeeRepository).deleteById(1L);
        Department department = new Department();
        department.setId(1L);
        department.setName("HumanResources");
        when(mockDepartmentRepository.getByName("HumanResources")).thenReturn(Optional.of(department));
        when(mockDepartmentRepository.getByName("ExceptionTrigger")).thenThrow(new RuntimeException("StubbedRuntimeException"));
        doNothing().when(mockEventMessageService).sendMessage(any(EventMessage.class));

    }

    @Test
    public void testSaveEmployee_noEmail_badRequest() {
        final EmployeeRequest employeeRequest = new EmployeeRequest();
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.CREATE);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("101", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals(mockApplicationProperties.getMessages().get("101"), ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testSaveEmployee_saveExistingUser_badRequest() {
        final EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("test@email.com");
        employeeRequest.setDepartmentName("marketing");
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.CREATE);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("102", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals(mockApplicationProperties.getMessages().get("102"), ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testSaveEmployee_saveExistingEmployee_badRequest() {
        final EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("test@email.com");
        employeeRequest.setDepartmentName("HumanResources");
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.CREATE);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("103", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals(mockApplicationProperties.getMessages().get("103"), ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testSaveEmployee_updateNonExistingEmployee_badRequest() {
        final EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("notexisting@email.com");
        employeeRequest.setDepartmentName("HumanResources");
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.UPDATE);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("104", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals(mockApplicationProperties.getMessages().get("104"), ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testSaveEmployee_repositoryFailing_InternalServerError() {
        final EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("notexisting@email.com");
        employeeRequest.setDepartmentName("ExceptionTrigger");
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.UPDATE);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("java.lang.RuntimeException", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals("StubbedRuntimeException", ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testSaveEmployee_Success() {
        Employee mockedEmployee = new Employee();
        employee.setId(2L);
        employee.setEmail("mocked@email.com");
        when(mockEmployeeRepository.save(any(Employee.class))).thenReturn(mockedEmployee);

        final EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("test1@email.com");
        employeeRequest.setDepartmentName("HumanResources");
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.CREATE);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("200", ((GenericMessage<String>) result.getBody()).getMessage());
    }

    @Test
    public void testUpdateEmployee_Success() {
        Employee mockedEmployee = new Employee();
        employee.setId(2L);
        employee.setEmail("mocked@email.com");
        when(mockEmployeeRepository.save(any(Employee.class))).thenReturn(mockedEmployee);

        final EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("test@email.com");
        employeeRequest.setDepartmentName("HumanResources");
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.saveEmployee(employeeRequest, CRUD_OPERATIONS.UPDATE);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("200", ((GenericMessage<String>) result.getBody()).getMessage());
    }

    @Test
    public void testGetEmployee_returnsValue_Success() {
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.getEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("200", ((GenericMessage<Employee>) result.getBody()).getMessage());
        assertEquals("test@email.com", ((GenericMessage<Employee>) result.getBody()).getMessageBody().getEmail());
    }

    @Test
    public void testGetEmployee_noEmployee_badRequest() {
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.getEmployee(2L);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("105", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals("No such employee in the records.", ((GenericMessage<String>) result.getBody()).getMessageBody());
    }

    @Test
    public void testDeleteEmployee_existingEmployee_Success() {
        when(mockEmployeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockApplicationProperties.getMessages().get("106"), ((GenericMessage<Employee>) result.getBody()).getMessage());
        assertEquals("test@email.com", ((GenericMessage<Employee>) result.getBody()).getMessageBody().getEmail());
    }

    @Test
    public void testDeleteEmployee_noEmployee_badRequest() {
        final ResponseEntity<GenericMessage> result = employeeServiceUnderTest.deleteEmployee(2L);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("105", ((GenericMessage<String>) result.getBody()).getMessage());
        assertEquals("No such employee in the records.", ((GenericMessage<String>) result.getBody()).getMessageBody());
    }
}
