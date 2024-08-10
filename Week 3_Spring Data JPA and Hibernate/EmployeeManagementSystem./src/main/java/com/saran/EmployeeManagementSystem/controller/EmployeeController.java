package com.saran.EmployeeManagementSystem.controller;

import com.saran.EmployeeManagementSystem.Models.Employee;
import com.saran.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Optional<Employee> emp = employeeRepository.findById(id);
            if (emp.isPresent()) {
                Employee employee1 =emp.get();
                employee1.setEmployeeName(employee.getEmployeeName());
                employee1.setEmail(employee.getEmail());
                employee1.setDepartment(employee.getDepartment());
                employeeRepository.save(employee1);
            }
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
}
