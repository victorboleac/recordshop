FROM openjdk:21-jdk-slim
LABEL maintainer="victor.boleac@icloud.com"
LABEL description="Record Shop API"
WORKDIR /app
COPY target/recordshop-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
