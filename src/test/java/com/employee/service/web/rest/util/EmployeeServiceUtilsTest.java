package com.employee.service.web.rest.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceUtilsTest {

    @Test
    public void testGetLocalDate() {
        LocalDate result = EmployeeServiceUtils.getLocalDate(null);
        assertEquals(null, result);
        result = EmployeeServiceUtils.getLocalDate("");
        assertEquals(null, result);
        result = EmployeeServiceUtils.getLocalDate("06.07.1988");
        assertTrue(result != null);
    }
}
