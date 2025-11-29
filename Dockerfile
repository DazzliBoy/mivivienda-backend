# Imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado por Maven
COPY target/mivivienda-0.0.1-SNAPSHOT.jar app.jar

# Comando de arranque
ENTRYPOINT ["java","-jar","app.jar"]
