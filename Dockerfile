# Базовый образ, содержащий java 17
FROM openjdk:17-oracle

# Директория приложения внутри контейнера
WORKDIR /app

# Копируем файлы в контейнер
COPY build/libs/studentregistration-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/data/default-students.txt /root/data/default-students.txt

# Определяем переменные среды
ENV STUDENT_REGISTER_INIT=true
ENV STUDENT_REGISTER_INPUT_PATH=/root/data/default-students.txt
ENV STUDENT_REGISTER_ALWAYS_CAN_DELETE=true

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]