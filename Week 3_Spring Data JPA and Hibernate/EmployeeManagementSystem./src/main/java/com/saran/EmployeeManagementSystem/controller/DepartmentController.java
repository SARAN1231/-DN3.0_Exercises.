package com.saran.EmployeeManagementSystem.controller;

import com.saran.EmployeeManagementSystem.Models.Department;
import com.saran.EmployeeManagementSystem.Models.Employee;
import com.saran.EmployeeManagementSystem.Repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDepartment(@RequestBody Department department) {
        departmentRepository.save(department);
        return new ResponseEntity<>("Department added", HttpStatus.CREATED);
    }

    @PutMapping("/update/{deptid}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable Long deptid) {
        Department dept = departmentRepository.findById(deptid).get();
        dept.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(dept);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{deptid}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long deptid) {
        departmentRepository.deleteById(deptid);
        return new ResponseEntity<>("Department deleted", HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<Employee>> getDepartmentById(@PathVariable Long id) {
        List<Employee> employees = departmentRepository.findById(id).get().getEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
