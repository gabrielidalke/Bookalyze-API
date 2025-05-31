# Use uma imagem base com o OpenJDK 17
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o JAR gerado pelo Maven para o contêiner
COPY target/bookalyze-api-0.0.1-SNAPSHOT.jar /app/bookalyze-api.jar

# Exponha a porta em que o Spring Boot vai rodar
EXPOSE 8081

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "bookalyze-api.jar"]
