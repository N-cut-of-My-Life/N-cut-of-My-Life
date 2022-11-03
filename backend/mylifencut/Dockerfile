FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/mylifencut-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY ./h2 h2
RUN chmod +x ./h2/bin/h2.sh
RUN ./h2/bin/h2.sh
ENTRYPOINT ["java","-jar","/app.jar"]