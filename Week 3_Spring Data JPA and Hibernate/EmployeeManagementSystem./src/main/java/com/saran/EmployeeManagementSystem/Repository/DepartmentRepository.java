package com.saran.EmployeeManagementSystem.Repository;

import com.saran.EmployeeManagementSystem.Models.Department;
import com.saran.EmployeeManagementSystem.Projections.DepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);

    List<DepartmentProjection> findAllProjectedBy();
}
