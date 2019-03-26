package com.employee.service.web.rest.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeServiceUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate getLocalDate(String date){
        return LocalDate.parse(date,formatter);
    }

}
