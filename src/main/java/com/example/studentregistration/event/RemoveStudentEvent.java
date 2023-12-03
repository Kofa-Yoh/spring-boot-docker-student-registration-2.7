package com.example.studentregistration.event;

import com.example.studentregistration.Student;
import lombok.Getter;

@Getter
public class RemoveStudentEvent implements CustomStudentEvent{

    private Student student;
    private String message;

    public RemoveStudentEvent(Student student) {
        this.student = student;
        message = "Удален из списка студент: " + student.getId();
    }
}
