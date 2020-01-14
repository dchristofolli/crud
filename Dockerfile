FROM openjdk:11
COPY ./build/libs/crud-1.0.jar /usr/src/crud/app.jar
WORKDIR /usr/src/crud
LABEL AUTHOR="Daniel Christofolli"
EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","app.jar"]