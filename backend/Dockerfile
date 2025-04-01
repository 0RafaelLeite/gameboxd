FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o código fonte e o pom.xml
COPY pom.xml .
COPY src ./src

# Compila o projeto
RUN mvn clean package -DskipTests

# Imagem final leve
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/gameboxd-backend-*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]