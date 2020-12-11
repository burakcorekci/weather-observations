FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine as build
EXPOSE 8080
ARG JAR_FILE=build/libs/springbootdocker-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]