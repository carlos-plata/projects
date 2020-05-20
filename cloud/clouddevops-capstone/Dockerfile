# Start from a small base
FROM openjdk:8-jdk-alpine

# Add our application binary
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run our application!
ENTRYPOINT ["java","-jar","/app.jar"]
