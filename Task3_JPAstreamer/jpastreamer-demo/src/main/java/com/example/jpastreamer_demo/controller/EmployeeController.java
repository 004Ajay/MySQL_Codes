package com.example.jpastreamer_demo.controller;

// package com.example.jpastreamer.controller;

import com.example.jpastreamer_demo.model.Employee;
import com.example.jpastreamer_demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/countByGender")
    public Map<Employee.Gender, Long> countByGender() {
        return employeeService.countByGender();
    }
}
