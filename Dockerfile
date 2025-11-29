# =========================
# Etapa 1: Construir el proyecto
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código
COPY src ./src

# Generar el jar
RUN mvn clean package -DskipTests

# =========================
# Etapa 2: Ejecutar el proyecto
# =========================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el jar desde la etapa de build
COPY --from=build /app/target/mivivienda-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

