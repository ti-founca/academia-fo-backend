# Etapa 1: compilar
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
RUN ./gradlew dependencies --no-daemon > /dev/null || true
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# Etapa 2: imagen final, solo con el JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd -r -u 1001 spring
COPY --from=build /app/build/libs/app.jar app.jar
USER spring
EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]