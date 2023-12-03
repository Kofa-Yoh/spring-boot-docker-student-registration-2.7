package com.example.studentregistration.config;

import com.example.studentregistration.err.CustomExceptionResolver;
import com.example.studentregistration.StudentMap;
import com.example.studentregistration.StudentMapService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.example")
@Configuration
@PropertySource("classpath:config/application.properties")
public class StudentMapConfig {

    @Bean
    public StudentMap studentMap() {
        return new StudentMap();
    }

    @Bean
    public StudentMapService studentMapService() {
        return new StudentMapService(studentMap());
    }

    @Bean
    CustomExceptionResolver customExceptionResolver() {
        return new CustomExceptionResolver();
    }
}
