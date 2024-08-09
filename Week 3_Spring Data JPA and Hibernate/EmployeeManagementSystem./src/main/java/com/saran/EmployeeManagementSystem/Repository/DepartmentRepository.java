package com.saran.EmployeeManagementSystem.Repository;

import com.saran.EmployeeManagementSystem.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);
}
