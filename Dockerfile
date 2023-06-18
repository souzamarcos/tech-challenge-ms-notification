#Build
FROM gradle:7.6-jdk19 AS BUILD_STAGE

COPY --chown=gradle:gradle . /home/gradle

WORKDIR /home/gradle

RUN gradle clean assemble --stacktrace --info

RUN mkdir -p /home/gradle/distribution/app

RUN unzip /home/gradle/application/build/distributions/application-0.0.1-SNAPSHOT.zip -d /home/gradle/distribution/app

#Application
FROM eclipse-temurin:19-jdk-jammy

COPY --from=BUILD_STAGE /home/gradle/distribution/app /opt/app

WORKDIR /opt/app

EXPOSE 8080

CMD ["/opt/app/application-0.0.1-SNAPSHOT/bin/application"]