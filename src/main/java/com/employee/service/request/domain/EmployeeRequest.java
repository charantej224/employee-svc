package com.employee.service.request.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeRequest {

    @JsonProperty("email")
    private String email;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("birthDay")
    private String birthDay;
    @JsonProperty("departmentName")
    private String departmentName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
            "email='" + email + '\'' +
            ", fullName='" + fullName + '\'' +
            ", birthDay='" + birthDay + '\'' +
            ", departmentName='" + departmentName + '\'' +
            '}';
    }
}
