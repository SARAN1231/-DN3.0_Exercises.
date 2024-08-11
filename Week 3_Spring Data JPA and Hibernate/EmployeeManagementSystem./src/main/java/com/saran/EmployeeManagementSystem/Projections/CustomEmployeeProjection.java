package com.saran.EmployeeManagementSystem.Projections;


import org.springframework.beans.factory.annotation.Value;

public interface CustomEmployeeProjection {
    @Value("#{target.employeeName + ' (' + target.email + ')'}")
    String getFullNameWithEmail();
}