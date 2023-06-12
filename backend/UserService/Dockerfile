FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY target/userservice-0.0.1-SNAPSHOT.jar userservice.jar
CMD ["java", "-jar" ,"userservice.jar"]