FROM openjdk:17
ADD target/ev-data-mgmt-springboot-app.jar ev-data-mgmt-springboot-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ev-data-mgmt-springboot-app.jar"]