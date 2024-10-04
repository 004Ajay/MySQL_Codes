package com.example.jpastreamer_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class JpastreamerDemoApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(JpastreamerDemoApplication.class, args);
// 	}

// }


@SpringBootApplication(scanBasePackages = "com.example.jpastreamer_demo")
public class JpastreamerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpastreamerDemoApplication.class, args);
    }
}
