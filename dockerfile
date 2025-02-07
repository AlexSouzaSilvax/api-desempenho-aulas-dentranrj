FROM eclipse-temurin:17-jdk-alpine
ENV TZ=America/Sao_Paulo
VOLUME /tmp
COPY target/api-desempenho-aulas-detranrj-0.0.1-SNAPSHOT.jar api-desempenho-aulas-detranrj.jar
ENTRYPOINT ["java","-Duser.timezone=America/Sao_Paulo","-jar","/api-desempenho-aulas-detranrj.jar"]