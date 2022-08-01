FROM openjdk:8-jre-alpine
MAINTAINER Dmytro Kuian <Dmytro_Kuian@epam.com>
COPY ["target/dmytro-kuian-1.0-SNAPSHOT.jar", "dmytro-kuian-1.0-SNAPSHOT.jar"]
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "dmytro-kuian-1.0-SNAPSHOT.jar"]