package com.example.studentregistration.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener {

    @EventListener
    public void listen(CustomStudentEvent event) {
        System.out.println(event.getMessage());
    }
}
