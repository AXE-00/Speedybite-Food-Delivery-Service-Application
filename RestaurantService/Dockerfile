FROM ubuntu:latest
FROM openjdk

LABEL authors="AXE"

COPY target/*.jar /restaurant-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/restaurant-service.jar"]