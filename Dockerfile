# Using multistage docker build
# Ref: https://docs.docker.com/develop/develop-images/multistage-build/

# Temp container to build using gradle
FROM gradle:8.3.0-jdk17-jammy AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

# App container
FROM eclipse-temurin:17-jdk-jammy
ENV ARTIFACT_NAME=fourlanches-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app

WORKDIR $APP_HOME/
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}