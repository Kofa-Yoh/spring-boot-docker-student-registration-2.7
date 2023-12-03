package com.example.studentregistration;

import com.example.studentregistration.event.AddStudentEvent;
import com.example.studentregistration.event.RemoveStudentEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class StudentMap {

    private final Map<Integer, Student> students = new HashMap<>();

    @Autowired
    private ApplicationEventPublisher publisher;

    public boolean putInMap(Student student) {
        if (student == null) {
            return false;
        }
        students.put(student.getId(), student);
        publisher.publishEvent(new AddStudentEvent(student));
        return true;
    }

    public Student removeFromMap(Integer id) {
        Student removedStudent = students.remove(id);
        if (removedStudent != null) {
            publisher.publishEvent(new RemoveStudentEvent(removedStudent));
        }
        return removedStudent;
    }

    public void removeAllStudents() {
        students.clear();
    }
}
