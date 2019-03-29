package com.employee.service.web.rest;

import com.employee.service.domain.Department;
import com.employee.service.repository.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    }

    @Test
    public void testCreateDepartment() throws Exception {
        // Setup
        final Department department = null;
        final ResponseEntity<Department> expectedResult = null;

        // Run the test
        final ResponseEntity<Department> result = departmentResourceUnderTest.createDepartment(department);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        // Setup
        final Department department = null;
        final ResponseEntity<Department> expectedResult = null;

        // Run the test
        final ResponseEntity<Department> result = departmentResourceUnderTest.updateDepartment(department);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllDepartments() {
        // Setup
        final List<Department> expectedResult = Arrays.asList();

        // Run the test
        final List<Department> result = departmentResourceUnderTest.getAllDepartments();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDepartment() {
        // Setup
        final Long id = 0L;
        final ResponseEntity<Department> expectedResult = null;

        // Run the test
        final ResponseEntity<Department> result = departmentResourceUnderTest.getDepartment(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteDepartment() {
        // Setup
        final Long id = 0L;
        final ResponseEntity<Void> expectedResult = null;

        // Run the test
        final ResponseEntity<Void> result = departmentResourceUnderTest.deleteDepartment(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
