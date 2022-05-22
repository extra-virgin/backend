# syntax=docker/dockerfile:1

# temp container to build
FROM openjdk:11-jdk-oracle AS builder
RUN microdnf install findutils

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew ./
COPY gradle/ ./gradle/

COPY src/ ./src/
RUN AP=$(find . -name "application.properties"); DAP=$(find . -name "docker.application.properties"); mv ./$DAP $AP
RUN chmod a+x ./gradlew
RUN ./gradlew build --no-daemon


# actual container
FROM adoptopenjdk/openjdk11:alpine-jre
ENV ARTIFACT_NAME=TinkoffTradingRobot-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/$ARTIFACT_NAME ./app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
