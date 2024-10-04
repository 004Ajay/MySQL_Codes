package com.example.jpastreamer_demo.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.example.jpastreamer_demo.model.Employee; // Adjust based on your entity package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private JPAStreamer jpaStreamer;

    // Change the return type to long
    public long getEmployeeCount() {
        // Count the total number of employees
        return jpaStreamer.stream(Employee.class).count(); // This returns long
    }
}
