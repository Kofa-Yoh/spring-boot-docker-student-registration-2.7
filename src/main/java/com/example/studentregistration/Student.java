package com.example.studentregistration;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class Student {

    private static int generator;
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;

    public Student(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        generator++;
        this.id = generator;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Студент {0} - {1} {2} {3}", id, firstName, lastName, age);
    }
}
