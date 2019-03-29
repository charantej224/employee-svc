package com.employee.service.web.rest;

import com.employee.service.domain.Department;
import com.employee.service.repository.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DepartmentResourceTest {

    @Mock
    private DepartmentRepository mockDepartmentRepository;
    @Mock
    private HttpHeaders mockHttpHeaders;

    @InjectMocks
    private DepartmentResource departmentResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        Department mockedDepartment = new Department();
        mockedDepartment.setName("mockedDepartment");
        when(mockDepartmentRepository.save(any(Department.class))).thenReturn(mockedDepartment);
        when(mockDepartmentRepository.save(null)).thenThrow(new NullPointerException());

    }

    @Test
    public void testCreateDepartment_validInputs() throws Exception {
        Department department = new Department();
        department.setName("Consulting");
        final ResponseEntity<Department> result = departmentResourceUnderTest.createDepartment(department);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("mockedDepartment", result.getBody().getName());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateDepartment_throwsException() throws Exception {
        departmentResourceUnderTest.createDepartment(null);
    }
}
