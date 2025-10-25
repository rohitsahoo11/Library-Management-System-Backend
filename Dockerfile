# Use Maven to build the project
FROM maven:3.8.7-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install

# Use a lightweight JDK image to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/LibraryManagementSystem-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]