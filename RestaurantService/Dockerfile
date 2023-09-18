FROM ubuntu:latest
FROM openjdk

LABEL authors="AXE"

COPY target/*.jar /restaurant-service.jar

ENTRYPOINT ["java", "-jar", "/restaurant-service.jar"]