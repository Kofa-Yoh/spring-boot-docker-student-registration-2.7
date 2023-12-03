package com.example.studentregistration;

import com.example.studentregistration.event.CustomAppStartedListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
@EnableCommand(StudentRegistrationShell.class)
@RequiredArgsConstructor
public class StudentRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(StudentRegistrationApplication.class);
        springApplication.addListeners(new CustomAppStartedListener());
        springApplication.run(args);
    }
}
