FROM maven:3.8.6-eclipse-temurin-19-focal as builder

WORKDIR /notification-service
COPY . .
RUN mvn clean dependency:resolve package -Dmaven.test.skip=true

FROM eclipse-temurin:19-jdk-focal

COPY --from=builder /notification-service/target/notification-service-0.0.1-SNAPSHOT.jar .
RUN chmod -R 777 notification-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/notification-service-0.0.1-SNAPSHOT.jar"]