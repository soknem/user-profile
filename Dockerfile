FROM gradle:8.8-jdk21-alpine AS setup
WORKDIR /project
COPY . .
RUN ./gradlew build -x test

FROM ghcr.io/graalvm/jdk-community:21 AS builder
WORKDIR /extracted
COPY --from=setup /project/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM ghcr.io/graalvm/jdk-community:21
WORKDIR /application
COPY --from=builder /extracted/dependencies/ ./
COPY --from=builder /extracted/spring-boot-loader/ ./
COPY --from=builder /extracted/snapshot-dependencies/ ./
COPY --from=builder /extracted/application/ ./

EXPOSE 8086
# Expose the port that the app runs on

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]