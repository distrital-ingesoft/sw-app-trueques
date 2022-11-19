# Compilación en docker

# Paso 1: MAVEN_BUILD
# ===================
# Compila usando maven 3.8.5
FROM maven:3.8.5-openjdk-17 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package -Pprod -DskipTests

# Paso 2: RUN
# ===========
# Ejecuta usando Java 17
FROM openjdk:17-jdk-alpine

# copia los .jar compilados en el paso 1
COPY --from=MAVEN_BUILD /build/target/*.jar /app.jar

# este el proceso del contenedor
# se inicia luego de iniciar el contenedor
# el contenedor termina cuando este proceso termina
ENTRYPOINT ["java","-jar","/app.jar"]
