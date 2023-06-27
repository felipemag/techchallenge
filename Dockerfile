FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

COPY build/libs/fourlanches-0.0.1-SNAPSHOT.jar fourlanches.jar

ENTRYPOINT [ "java","-jar","/fourlanches.jar" ]

EXPOSE 8080