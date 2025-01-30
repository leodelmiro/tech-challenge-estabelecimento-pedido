FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/pedido-0.0.1-SNAPSHOT.jar app.jar

ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]