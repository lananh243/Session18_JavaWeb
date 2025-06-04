package com.data.controller;

import com.data.dto.DepartmentDTO;
import com.data.entity.Department;
import com.data.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @GetMapping("department")
    public String getAll(Model model) {
        List<Department> departments = departmentService.findAll();
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();

        departments.forEach(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setDepartmentName(department.getDepartmentName());
            departmentDTOs.add(departmentDTO);
        });

        System.out.println(departmentDTOs);

        model.addAttribute("departmentDTOs", departmentDTOs);
        return "department_list";
    }
}
