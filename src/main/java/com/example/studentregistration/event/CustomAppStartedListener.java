package com.example.studentregistration.event;

import com.example.studentregistration.StudentMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomAppStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${students.register.init}")
    private boolean neededInitialization;

    @Value("${students.register.input}")
    private String inputPath;

    @Autowired
    private StudentMapService studentMapService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        if (studentMapService == null) return;
        if (!neededInitialization) return;
        if (inputPath == null || inputPath.isEmpty()) return;

        studentMapService.initializeDefaultStudents(inputPath);
    }
}
