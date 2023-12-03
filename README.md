# Task 2.7 Student Registration in Spring Shell
The project presents simple CLI app made with Java and Spring Boot.
A user can add a new students in the registration list, remove one or all students from the list, see all registered students.
Also a user can build the application's image in docker and run it with individual settings.

## Themes
- Creating an application “Student Registration” with Spring Shell.
- Create commands for saving, deleting, viewing and clearing students.
- Create delete and create student listeners.
- Manage a default student list through application settings.
- Dockerization of the application.

## Quick Start
1. Clone this repository
2. Create bootJar with gradle
3. Change properties in application.properties file or Dockerfile
4. Change students registration list in file `src/main/resources/data/default-students.txt`
5. Run application

#### Properties In Docker
```
# Dockerfile

ENV STUDENT_REGISTER_INIT=true
# true/false - On/Off registration students from file during application startup

ENV STUDENT_REGISTER_INPUT_PATH=/root/data/default-students.txt
# This is a path from docker file, if you want change it you need also change:
COPY src/main/resources/data/default-students.txt /root/data/default-students.txt 

ENV STUDENT_REGISTER_ALWAYS_CAN_DELETE=true
# true/false - To made command 'delete' always available in shell
```

## Docker commands to start application
```
docker build -t student-registration .
# Build an image from a Dockerfile

docker run --rm -it -e STUDENT_REGISTER_INIT=false student-registration
# Create and run a new container from an image with name 'student-registration'
# --rm - Automatically remove the container when it exits
# -it - Create an interactive bash shell in the container
# -e - Set environment variables
```

## Application commands
```
add -f Sara -l Connor -a 19
# Register a new student in the list. -f firstname, -l lastname -a age

delete --id 46
# Remove the student with id 46 from the list

delete --all
# Clear the registration list

print
# Print all students from the list
```