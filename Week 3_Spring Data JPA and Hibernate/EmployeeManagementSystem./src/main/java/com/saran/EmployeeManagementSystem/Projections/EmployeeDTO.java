package com.saran.EmployeeManagementSystem.Projections;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String employeeName;
    private String email;
    private String departmentName;

    public EmployeeDTO(String employeeName, String email, String departmentName) {
        this.employeeName = employeeName;
        this.email = email;
        this.departmentName = departmentName;
    }

}
