FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY application.properties /
ENTRYPOINT ["java","-jar","/app.jar","application.properties"]
