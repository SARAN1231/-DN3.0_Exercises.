package com.saran.EmployeeManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Department")
@Data
public class Department {

     @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    private String departmentName;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employees;

}
