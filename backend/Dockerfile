FROM eclipse-temurin:17.0.1_12-jdk
COPY . .
RUN ./mvnw clean install -DskipTests
ENTRYPOINT ["java", "-jar", "target/personal_projects-0.0.1-SNAPSHOT.jar"]