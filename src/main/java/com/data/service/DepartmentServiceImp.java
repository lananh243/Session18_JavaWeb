package com.data.service;

import com.data.entity.Department;
import com.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;
    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }
}
