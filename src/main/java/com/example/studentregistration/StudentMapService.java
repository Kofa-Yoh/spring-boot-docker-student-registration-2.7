package com.example.studentregistration;

import com.example.studentregistration.err.WrongParametersException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class StudentMapService {

    private final StudentMap studentMap;

    public void initializeDefaultStudents(String inputPath) {

        Path inputFile = Paths.get(inputPath);
        try {
            Files.lines(inputFile)
                    .map(line -> line.split(" "))
                    .forEach(words -> studentMap.putInMap(new Student(words[0], words[1], Integer.parseInt(words[2]))));
            System.out.println("Список студентов загружен. Всего студентов: " + getStudentsCount());
        } catch (IOException e) {
            System.out.println("При загрузке студентов произошла ошибка:\n" + e.getMessage());
        }
    }

    public void printAllStudents() {
        studentMap.getStudents().entrySet().forEach(System.out::println);
    }

    public Student addStudent(String firstName, String lastName, Integer age) throws WrongParametersException {
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                age == null || age == 0) {
            throw new WrongParametersException("Имя, фамилия и возраст студента должны быть заполнены.");
        }

        Student student = new Student(firstName, lastName, age);
        return studentMap.putInMap(student) ? student : null;
    }

    public Student removeStudentById(Integer id) throws WrongParametersException {
        if (id == null) {
            throw new WrongParametersException("Введите id студента.");
        }

        Student removedStudent = studentMap.removeFromMap(id);
        if (removedStudent == null) {
            throw new WrongParametersException("Студент с таким id(" + id + ") не найден.");
        }
        return removedStudent;
    }

    public void removeAllStudents() {
        studentMap.removeAllStudents();
    }

    public int getStudentsCount() {
        return studentMap.getStudents().size();
    }
}
