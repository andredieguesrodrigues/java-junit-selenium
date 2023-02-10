FROM maven:3.6.3-jdk-11-openj9
WORKDIR /sample
COPY src /sample/src
COPY pom.xml /sample
