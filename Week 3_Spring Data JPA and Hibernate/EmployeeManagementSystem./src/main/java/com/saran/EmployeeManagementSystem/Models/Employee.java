package com.saran.EmployeeManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String employeeName;
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
}
