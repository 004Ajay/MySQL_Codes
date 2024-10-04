package com.example.jpastreamer_demo.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.example.jpastreamer_demo.model.Employee; // Adjust based on your entity package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private JPAStreamer jpaStreamer;

    public int getEmployeeCount() {
        // Cast long to int, but be cautious of overflow
        return (int) jpaStreamer.stream(Employee.class).count(); // This will cast to int
    }
}
