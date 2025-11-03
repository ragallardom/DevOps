FROM eclipse-temurin:23-jdk AS builder
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
COPY src ./src
# Copy optional Wallet directory if present
COPY Wallet ./Wallet
RUN chmod +x mvnw && \
    find /app -type d -name "Wallet" -exec chmod -R 755 {} + || true
RUN ./mvnw -B -U -DskipTests clean package

FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/Wallet /app/Wallet
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
