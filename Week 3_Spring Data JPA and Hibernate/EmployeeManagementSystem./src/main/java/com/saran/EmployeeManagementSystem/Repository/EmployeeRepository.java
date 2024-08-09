package com.saran.EmployeeManagementSystem.Repository;

import com.saran.EmployeeManagementSystem.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment_DepartmentName(String departmentName);
}
