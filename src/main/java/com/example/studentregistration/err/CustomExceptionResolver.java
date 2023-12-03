package com.example.studentregistration.err;

import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;

public class CustomExceptionResolver implements CommandExceptionResolver {
    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if (ex instanceof WrongParametersException) {
            return CommandHandlingResult.of(ex.getMessage() + "\n");
        }
        return CommandHandlingResult.of("Ошибка. Обратитесь к администратору.\n" + ex.getMessage() + "\n");
    }
}
