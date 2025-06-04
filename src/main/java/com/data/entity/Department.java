package com.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id // khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng
    private int id;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
