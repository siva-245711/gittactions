# Use the OpenJDK 17 base image
FROM openjdk:17-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and the pom.xml
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Copy the entire project source code
COPY src ./src

# Ensure the Maven wrapper is executable
RUN chmod +x ./mvnw

# Package the application
RUN ./mvnw package

# Use a separate OpenJDK 17 runtime image for the final build
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/movie-api-0.0.1-SNAPSHOT.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "app.jar"]
