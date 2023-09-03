FROM ubuntu:latest
FROM openjdk

LABEL authors="AXE"

COPY target/*.jar /user-auth-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/user-auth-service.jar"]