package com.data.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class Employee {
    @Id // khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng
    private int id;

    private String fullName;

    private String email;

    private String phone;

    // ORM: Object-Relational Mapping
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
