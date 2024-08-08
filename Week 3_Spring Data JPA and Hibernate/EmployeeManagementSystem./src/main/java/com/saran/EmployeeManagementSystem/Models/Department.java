package com.saran.EmployeeManagementSystem.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Department")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    private String departmentName;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Employee> employees;

}
