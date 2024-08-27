### Docker Tutorial: Running a Base Image and Adding Code

This tutorial will guide you through running a base Docker image (`openjdk`) and then adding your Java code using a Dockerfile.

#### **Part 1: Running the Base OpenJDK Image**

**Step 1: Install Docker**
- **If you haven't already installed Docker,** you'll need to do so. Download and install Docker from the [official website](https://www.docker.com/products/docker-desktop/).

**Step 2: Pull the OpenJDK Base Image**
- Open your terminal (Command Prompt, PowerShell, or terminal on macOS/Linux).
- Pull the OpenJDK base image from Docker Hub using the following command:
  ```bash
  docker pull openjdk:17-jdk-slim
  ```

**Step 3: Run the OpenJDK Base Image**
- Now, run a container using the OpenJDK image:
  ```bash
  docker run -it openjdk:17-jdk-slim /bin/bash
  ```
- **Explanation:**
  - `-it`: Interactive mode with a terminal session.
  - `openjdk:17-jdk-slim`: The name of the base image.
  
- Once the container is running, you'll be inside the container's terminal. Here, you can run basic Java commands, but there's no application code yet—this is just the Java environment.

**Step 4: Explore the Container**
- While inside the container, you can explore the file system and environment.
  ```bash
  java -version
  ```
- This command will show the version of Java installed in the container, confirming that you are indeed running Java in the Docker environment.

**Step 5: Exit the Container**
- To exit the container, simply type `exit` and press Enter.

---

#### **Part 2: Adding Your Code Using a Dockerfile**

**Step 1: Create a Dockerfile**
- **Explanation:** A Dockerfile is a script that automates the process of building a Docker image. It specifies the base image, copies your application files, and defines how to run your application.

1. **Navigate to Your Project Directory:**
   - Open your terminal and navigate to `C:\Users\gonch\Downloads\demo\demo`.

2. **Create a Dockerfile:**
   - In the root of your project (`C:\Users\gonch\Downloads\demo\demo`), create a file named `Dockerfile`.

**Step 2: Write the Dockerfile**
- Add the following content to your Dockerfile:
  ```dockerfile
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

  ```

**Step 3: Build the Docker Image**
- Now that you have a Dockerfile, build your Docker image using the following command:
  ```bash
  docker build -t movie-api-app .
  ```
- **Explanation:**
  - `-t movie-api-app`: Tags the image with the name `movie-api-app`.
  - `.`: Specifies the current directory as the context for the Docker build.

**Step 4: Run the Container with Your Application**
- After building the image, run it using the following command:
  ```bash
  docker run -p 8080:8080 movie-api-app
  ```
- **Explanation:**
  - `-p 8080:8080`: Maps port 8080 on your local machine to port 8080 in the container.
  - `movie-api-app`: The name of the Docker image.

**Step 5: Access Your Application**
- With the container running, open a web browser and navigate to `http://localhost:8080/hello` or `http://localhost:8080/api/movies/popular`. You should see the responses from your Spring Boot application.

**Step 6: Stop the Container**
- To stop the container, press `Ctrl + C` in the terminal where it's running.

---

### Recap

1. **Pulled and ran the OpenJDK base image** to understand the environment your application will run in.
2. **Created a Dockerfile** to define how to package and run your Spring Boot application.
3. **Built and ran a Docker container** from that image, successfully deploying your application.

This tutorial helps you understand how to leverage Docker for a Java application by starting with a base image and layering your application code on top.

----------------------------

Here are some exercises designed to reinforce the concepts learned in the tutorial:

### **Exercise 1: Exploring the Base Image**
**Objective:** Understand the base image environment and the tools available within it.

**Instructions:**
1. Pull and run the `openjdk:17-jdk-slim` base image as shown in the tutorial.
2. Once inside the container, list all installed packages using the following command:
   ```bash
   dpkg -l
   ```
3. Try running a simple Java program inside the container. 
   - Create a file named `HelloWorld.java` using a text editor like `nano` or `vi`.
   - Write a simple "Hello, World!" program in Java.
   - Compile and run it inside the container.
   ```bash
   javac HelloWorld.java
   java HelloWorld
   ```
4. Exit the container.

**Questions:**
- What packages are installed by default in the `openjdk:17-jdk-slim` image?
- How does the environment inside the container differ from your local machine?

### **Exercise 2: Customizing the Dockerfile**
**Objective:** Modify the Dockerfile to better understand how it controls the image-building process.

**Instructions:**
1. Add an environment variable to the Dockerfile that specifies the name of the application.
   ```dockerfile
   ENV APP_NAME=MovieAPI
   ```
2. Modify the `CMD` instruction to print the `APP_NAME` environment variable before running the application.
   ```dockerfile
   CMD echo "Starting $APP_NAME..." && java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```
3. Rebuild the Docker image and run it.
   ```bash
   docker build -t movie-api-app-custom .
   docker run -p 8080:8080 movie-api-app-custom
   ```

**Questions:**
- What changes do you notice when running the container after adding the `ENV` variable and modifying the `CMD` instruction?
- How can you further customize the environment in your Docker container?

### **Exercise 3: Working with Volumes**
**Objective:** Understand how to persist data in Docker containers using volumes.

**Instructions:**
1. Modify your Dockerfile to use an external configuration file for your Spring Boot application.
   - Create a `config` directory on your local machine and add a `custom-application.properties` file with some Spring Boot configurations.
2. Update your Dockerfile to copy this configuration file into the container.
   ```dockerfile
   COPY config/custom-application.properties /app/config/application.properties
   ```
3. Rebuild the Docker image and run the container with a volume that maps your local `config` directory to the `/app/config` directory in the container.
   ```bash
   docker run -p 8080:8080 -v $(pwd)/config:/app/config movie-api-app
   ```
4. Check if the application runs with the custom configuration.

**Questions:**
- How does using a volume affect the container's ability to use external resources?
- What are some potential use cases for using volumes in Docker?

### **Exercise 4: Debugging a Docker Container**
**Objective:** Learn how to debug a running Docker container.

**Instructions:**
1. Run the Docker container in detached mode:
   ```bash
   docker run -d -p 8080:8080 movie-api-app
   ```
2. List all running containers and find the container ID of your running application:
   ```bash
   docker ps
   ```
3. Use the container ID to attach to the running container and explore the file system:
   ```bash
   docker exec -it <container_id> /bin/bash
   ```
4. Check the logs of the running container:
   ```bash
   docker logs <container_id>
   ```

**Questions:**
- What are some methods to troubleshoot issues in a running Docker container?
- How can you inspect and modify the state of a running container?

### **Exercise 5: Multi-Stage Builds**
**Objective:** Learn how to optimize Docker images using multi-stage builds.

**Instructions:**
1. Research multi-stage builds in Docker.
2. Modify your Dockerfile to use a multi-stage build. For example, use one stage to build the application and another to run it.
   ```dockerfile
   # Stage 1: Build
   FROM openjdk:17-jdk-slim as build
   WORKDIR /app
   COPY . /app
   RUN ./mvnw package

   # Stage 2: Run
   FROM openjdk:17-jdk-slim
   WORKDIR /app
   COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/
   EXPOSE 8080
   CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
   ```
3. Build and run the image.
   ```bash
   docker build -t movie-api-app-multi-stage .
   docker run -p 8080:8080 movie-api-app-multi-stage
   ```

**Questions:**
- What are the advantages of using multi-stage builds?
- How does the size of the Docker image compare before and after implementing multi-stage builds?

### **Summary**

These exercises are designed to solidify your understanding of Docker by having you explore the base image, customize Dockerfiles, work with volumes, debug containers, and optimize images using multi-stage builds. By completing these exercises, you should gain a deeper, more practical understanding of Docker and its capabilities.
