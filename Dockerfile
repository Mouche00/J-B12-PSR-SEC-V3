# Step 1: Use an official OpenJDK base image from Docker Hub
FROM openjdk:17-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file into the container
COPY ./target/*.jar app.jar

# Step 4: Expose the port your application runs on
EXPOSE 8443

# Step 5: Define the command to run your Spring Boot application using the Default profile
CMD ["java", "-Dspring.profiles.active=default", "-jar", "app.jar"]