package com.example.jpastreamer_demo.config;

import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAStreamerConfig {

    @Bean
    public JPAStreamer jpaStreamer() {
        return JPAStreamer.of("test_db"); // Replace "test_db" with your actual persistence unit name
    }
}
