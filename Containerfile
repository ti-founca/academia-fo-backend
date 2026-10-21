# Etapa de compilación
FROM docker.io/library/eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew && ./gradlew bootJar --no-daemon -x test

# Etapa de ejecución (solo JRE, imagen más liviana)
FROM docker.io/library/eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]