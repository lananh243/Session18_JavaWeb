package com.data.repository;

import com.data.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
}