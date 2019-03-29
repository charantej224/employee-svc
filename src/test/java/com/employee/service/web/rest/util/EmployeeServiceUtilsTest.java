package com.employee.service.web.rest.util;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class EmployeeServiceUtilsTest {

    @Test
    public void testGetLocalDate() {
        // Setup
        final String date = "date";
        final LocalDate expectedResult = LocalDate.of(2017, 1, 1);

        // Run the test
        final LocalDate result = EmployeeServiceUtils.getLocalDate(date);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
