# Step 1: Use an official lightweight Java runtime
FROM eclipse-temurin:21-jre-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the compiled jar file into the container
# Note: Replace 'your-app-name-0.0.1-SNAPSHOT.jar' with your actual jar filename
COPY build/libs/*-SNAPSHOT.jar app.jar

# Step 4: Expose the port your Spring Boot app runs on
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]