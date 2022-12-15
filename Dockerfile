#FROM maven
#
#RUN mkdir -p /app
#RUN mkdir -p /app/.m2
#
#COPY src /app/src
#COPY pom.xml /app
#
#WORKDIR /app
#
#ENV SPRING_PROFILES_ACTIVE=all,dev,local
#EXPOSE 8081
#ENV SERVER_PORT=8081
#ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2"
#
#ENTRYPOINT ["sh", "-c", "mvn -Dexec.mainClass=\"ProfileAPIApplication\" -Dsocket.soReuseAddress=true clean compile exec:java"]

FROM maven

RUN mkdir -p /app
RUN mkdir -p /app/.m2

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=all,dev,local
EXPOSE 8080
ENV SERVER_PORT=8080
ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2"

ENTRYPOINT ["sh", "-c", "mvn -Dexec.mainClass=\"com.gamerdates.profileapi.ProfileAPIApplication\" -Dsocket.soReuseAddress=true clean compile exec:java"]

