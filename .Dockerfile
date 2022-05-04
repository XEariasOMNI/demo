FROM maven:3.8.5
RUN java -version

COPY pom.xml /tmp/build/
RUN cd /tmp/build && mvn dependency:resolve

COPY src /tmp/build/src

RUN cd /tmp/build && mvn -DskipTests=true package
RUN ls -la
RUN cd /tmp/build && mv target/*.jar /app.jar
RUN cd / && rm -rf /tmp/build

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]