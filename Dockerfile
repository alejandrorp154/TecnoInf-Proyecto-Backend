FROM maven:3.6.3-openjdk-14-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY /home/runner/work/Backend/Backend/pryectoBack-web /workspace/src
RUN mvn -B package --file pom.xml

FROM openjdk:14-slim
COPY --from=build /home/runner/work/Backend/Backend/pryectoBack-web/target/*my-app.jar my-app.jar
EXPOSE 6379
ENTRYPOINT ["java","-jar","app.jar"]
