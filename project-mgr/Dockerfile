FROM openjdk:8
#FROM maven:onbuild AS buildenv
LABEL maintainer="sindhura587@gmail.com"
#WORKDIR /app
#COPY --from=buildenv app/target/projectmanagement-fsd.jar  projectmanagement-fsd.jar
ADD target/projectmanagement-fsd.jar  projectmanagement-fsd.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "projectmanagement-fsd.jar"]
