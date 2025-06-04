package com.data.controller;

import com.data.dto.EmployeeDTO;
import com.data.entity.Employee;
import com.data.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employee")
    public String getAll(Model model) {
        List<Employee> employees = employeeService.findAll();

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFullName(employee.getFullName());
            if (employee.getDepartment() != null) {
                employeeDTO.setDepartmentName(employee.getDepartment().getDepartmentName());
            } else {
                employeeDTO.setDepartmentName("No Department");
            }
            employeeDTOs.add(employeeDTO);
        }

        model.addAttribute("employeeDTOs", employeeDTOs);
        return "employee_list";
    }
}
