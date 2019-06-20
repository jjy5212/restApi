FROM openjdk:8-jdk
ADD target/restApiTest-0.0.1-SNAPSHOT.war /app.war
EXPOSE 85
ENTRYPOINT ["java","-jar","/app.war"]
