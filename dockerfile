FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV EUREKA_SERVER=http://eureka-server:8761/eureka/
ENV DB_HOST=mysql_authventas
ENV DB_PORT=3306
ENV DB_NAME=venta_sistema
ENV DB_USER=root
ENV DB_PASSWORD=123456789
ENV GATEWAY_URL=http://api-gateway:8080

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
