package com.employee.service.web.rest.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeServiceUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate getLocalDate(String date) {
        LocalDate birthDate = !StringUtils.isBlank(date) ? LocalDate.parse(date, formatter) : null;
        return birthDate;
    }
}
