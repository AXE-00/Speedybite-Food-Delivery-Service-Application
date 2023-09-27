FROM ubuntu:latest
FROM openjdk

LABEL authors="AXE"

COPY target/*.jar /payment-service.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/payment-service.jar"]