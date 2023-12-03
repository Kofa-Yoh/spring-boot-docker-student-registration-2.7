package com.example.studentregistration.event;

import com.example.studentregistration.Student;
import lombok.Getter;

@Getter
public class AddStudentEvent implements CustomStudentEvent{

    private Student student;
    private String message;

    public AddStudentEvent(Student student) {
        this.student = student;
        message = "Зарегистрирован: " + student;
    }
}
