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
  docker run -it openjdk:17-jdk-slim
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
  FROM openjdk:17-jdk-slim

  # Set the working directory in the container
  WORKDIR /app

  # Copy the current directory contents into the container at /app
  COPY . /app

  # Package the application (this assumes you're using Maven Wrapper)
  RUN ./mvnw package

  # Make port 8080 available to the world outside this container
  EXPOSE 8080

  # Run the jar file
  CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
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
