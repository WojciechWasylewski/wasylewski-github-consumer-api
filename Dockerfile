FROM eclipse-temurin:21-jre-alpine
COPY /target/gitagent.jar /gitagent.jar
ENTRYPOINT ["java","-jar","/gitagent.jar"]
