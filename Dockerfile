FROM openjdk:8-jdk-alpine

WORKDIR /

EXPOSE 8080

COPY build/libs/orders_api-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]