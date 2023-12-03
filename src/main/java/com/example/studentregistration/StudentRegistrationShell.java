package com.example.studentregistration;

import com.example.studentregistration.err.WrongParametersException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;

@Command
@RequiredArgsConstructor
public class StudentRegistrationShell {

    private final StudentMapService studentMapService;

    @Value("${students.register.always-can-delete}")
    private boolean canAlwaysDelete;

    @Command(command = "print")
    public void printAllStudents() {
        if (studentMapService.getStudentsCount() == 0) {
            System.out.println("Нет зарегистрированных студентов.");
            return;
        }
        studentMapService.printAllStudents();
    }

    @Command(command = "add")
    public void addNewStudents(@Option(shortNames = 'f') String firstname,
                               @Option(shortNames = 'l') String lastName,
                               @Option(shortNames = 'a') Integer age)
            throws WrongParametersException {

        Student student = studentMapService.addStudent(firstname, lastName, age);
        if (student == null) {
            throw new WrongParametersException(MessageFormat.format("Не добавлен: {0} {1} {2}. Обратитесь к администратору", firstname, lastName, age));
        }
    }

    @Command(command = "delete")
    @CommandAvailability(provider = "canRemoveStudents")
    public void removeStudent(@Option(longNames = "id", defaultValue = "-1", required = false) Integer id,
                              @Option(longNames = "all", defaultValue = "false", required = false) boolean all)
            throws WrongParametersException {

        if (all) {
            studentMapService.removeAllStudents();
            if (studentMapService.getStudentsCount() == 0) {
                System.out.println("Студенты удалены из списка зарегистрированных.");
                return;
            } else {
                throw new WrongParametersException("Студенты не удалены из списка зарегистрированных. Обратитесь к администратору.");
            }
        }

        if (id == null || id == -1) {
            throw new WrongParametersException("Введите id студента, команда: delete --id 0");
        }

        Student student = studentMapService.removeStudentById(id);
    }

    @Bean
    public AvailabilityProvider canRemoveStudents() {
        if (canAlwaysDelete) {
            return () -> Availability.available();
        }
        int count = studentMapService.getStudentsCount();
//        System.out.println("Кол-во студентов:" + count);
        return () -> count == 0
                ? Availability.unavailable("Список зарегистрированных студентов пуст.")
                : Availability.available();
    }
}
