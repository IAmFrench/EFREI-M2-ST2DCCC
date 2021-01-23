# FROM gradle:6.6.1-jdk14 AS build
FROM gradle:6.7.1-jdk15 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:15-alpine
LABEL org.opencontainers.image.source=https://github.com/IAmFrench/EFREI-M2-ST2DCCC
LABEL org.opencontainers.image.title="Actor Service"
LABEL org.opencontainers.image.description="Import movies from the Movie Service and inject actors into thems"
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "--enable-preview", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
