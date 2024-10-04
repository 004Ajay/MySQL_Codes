package com.example.jpastreamer_demo.service;

import com.example.jpastreamer_demo.model.Employee;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final JPAStreamer jpaStreamer;

    public EmployeeService(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    public Map<Employee.Gender, Long> countByGender() {
        return jpaStreamer.stream(Employee.class)
                          .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
    }
}
