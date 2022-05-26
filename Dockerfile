FROM openjdk:17-jdk-alpine3.14

COPY "./target/progkor-filmek.jar" "/application/progkor-filmek.jar"

CMD ["java", "-jar", "/application/progkor-filmek.jar"]