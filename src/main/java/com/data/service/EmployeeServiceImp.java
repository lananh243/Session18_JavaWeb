package com.data.service;

import com.data.entity.Employee;
import com.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;


    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
}
