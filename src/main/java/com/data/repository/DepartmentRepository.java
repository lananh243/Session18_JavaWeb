package com.data.repository;

import com.data.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();
}