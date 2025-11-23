
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom first and download dependencies
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copy entire source
COPY src ./src

# Package the application
RUN mvn -q clean package -DskipTests


FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copy the jar from the build container
COPY --from=build /app/target/*.jar app.jar

# Expose port (optional)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
