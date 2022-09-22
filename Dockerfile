FROM openjdk:11.0.12
EXPOSE 8080
ADD target/gama-post-and-comments.jar gama-post-and-comments.jar
ENTRYPOINT ["java", "-jar", "/gama-post-and-comments.jar"]