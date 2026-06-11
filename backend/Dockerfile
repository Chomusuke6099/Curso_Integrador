FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN addgroup -S tmo && adduser -S tmo -G tmo
COPY --from=build /app/target/tmo-backend-1.0.0.jar /app/tmo-backend.jar
USER tmo
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/tmo-backend.jar"]
