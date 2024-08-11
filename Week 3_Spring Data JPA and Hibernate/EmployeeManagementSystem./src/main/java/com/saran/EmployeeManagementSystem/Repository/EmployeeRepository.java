package com.saran.EmployeeManagementSystem.Repository;

import com.saran.EmployeeManagementSystem.Models.Employee;
import com.saran.EmployeeManagementSystem.Projections.CustomEmployeeProjection;
import com.saran.EmployeeManagementSystem.Projections.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment_DepartmentName(String departmentName);

    @Query(name = "Employee.findByEmail")
    Employee findByEmail(String email);

    @Query("select e from Employee e where e.employeeName = :employeeName")
    List<Employee> findEmployeeByEmployeeName(String employeeName);

    @Query("select e from Employee e  where e.department.departmentName = :deptnmae")
    List<Employee> findEmployeeByDepartment_DepartmentName(String deptnmae);

    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT new com.saran.EmployeeManagementSystem.Models.EmployeeDTO(e.employeeName, e.email, d.departmentName) " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeDTO> findAllEmployeeDTOs();

    List<CustomEmployeeProjection> findBy();
}
