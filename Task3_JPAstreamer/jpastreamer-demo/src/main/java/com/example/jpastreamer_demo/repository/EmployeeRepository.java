package com.example.jpastreamer_demo.repository;

// package com.example.jpastreamer.repository;

import com.example.jpastreamer_demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
