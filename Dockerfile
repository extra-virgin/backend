# syntax=docker/dockerfile:1

FROM openjdk:11-jdk-oracle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

