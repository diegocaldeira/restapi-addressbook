FROM openjdk:8-jdk-alpine as build
MAINTAINER diego caldeira <dev@diegocaldeira.com>

ARG JAR_FILE=target/restapi-addressbook-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080