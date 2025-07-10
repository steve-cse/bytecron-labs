FROM amazoncorretto:17-alpine-jdk
LABEL website="bytecron.me"
COPY target/bytecron-labs-0.0.1-SNAPSHOT.jar bytecron-labs-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bytecron-labs-0.0.1-SNAPSHOT.jar"]