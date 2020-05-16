
FROM codenvy/jdk8_maven3_tomcat8
VOLUME /tmp
ADD sl-web-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
EXPOSE 9008



