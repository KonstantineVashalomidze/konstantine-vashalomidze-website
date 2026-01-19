# --- Build Stage ---
# This stage uses a full JDK and Gradle to build the application JAR.
FROM eclipse-temurin:21-jdk AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and build scripts
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the application source code
COPY src ./src

# Build the application, skipping tests for a faster build.
# Using --no-daemon is recommended for CI/CD environments.
RUN ./gradlew build --no-daemon -x test


# --- Run Stage ---
# This stage uses a minimal JRE to run the application, resulting in a smaller final image.
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the builder stage
# The JAR file is found in the build/libs directory.
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Environment variables for application configuration
# These can be overridden at runtime with 'docker run -e'
ENV ADMIN_USERNAME=admin
ENV ADMIN_PASSWORD=changeme

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
