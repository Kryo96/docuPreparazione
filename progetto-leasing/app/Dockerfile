FROM maven:3.8-openjdk-17 AS build
WORKDIR /backend

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests


FROM openjdk:17-jdk-slim
WORKDIR /backend

COPY --from=build /backend/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
