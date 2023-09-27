FROM ubuntu:latest
FROM openjdk

LABEL authors="AXE"

COPY target/*.jar /api-gateway.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]