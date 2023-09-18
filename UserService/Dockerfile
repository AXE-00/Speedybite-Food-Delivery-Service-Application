FROM ubuntu:latest
FROM openjdk

LABEL authors="AXE"

COPY target/*.jar /user-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/user-service.jar"]