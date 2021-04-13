FROM maven:3.6.3-openjdk-14-slim AS build

EXPOSE 8080

COPY ./build/libs/pryectoBack-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "pryectoBack-1.0-SNAPSHOT.jar"]