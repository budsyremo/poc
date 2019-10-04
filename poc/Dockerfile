FROM openjdk:8-jre-slim  
ADD target/PlayerIntegrator.jar PlayerIntegrator.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","PlayerIntegrator.jar"]